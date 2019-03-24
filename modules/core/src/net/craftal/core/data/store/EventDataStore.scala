package net.craftal.core.data.store

import java.sql.Timestamp

import javax.inject.{Inject, Singleton}
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.EventEntityDataMapper
import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.core.domain.model.event.{Event, EventRepository}
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class EventDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends EventRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findEvent(eventId: Int): Future[Event] =
    dbConfig.db.run(
      Tables.Event.filter(_.id === eventId).result.head.map(EventEntityDataMapper.transform)
    )

  override def findEventsByTerm(termStart: DateTime,
                                termEnd: Option[DateTime],
                                keyword: Option[String]): Future[List[Event]] = {
    // Guard
    termEnd match {
      case Some(x) if x.isBefore(termStart) =>
        throw new IllegalArgumentException("termStart must be earlier than termEnd.")
      case _ =>
    }

    val query = for {
      e <- this.keywordQuery(keyword)
      s <- this.termsQuery(termStart, termEnd)
      p <- Tables.EventPhoto
      if e.id === s.eventId
    } yield (e, s, p)

    dbConfig
      .db
      .run(
        query.to[List].result.map(EventEntityDataMapper.transformCollection)
      )
  }

  override def findEventsByKeyword(keyword: String): Future[List[Event]] = {
    val query = for {
      e <- this.keywordQuery(Option(keyword))
      s <- Tables.EventSchedule
      p <- Tables.EventPhoto
      if e.id === s.eventId && e.id === p.eventId
    } yield (e, s, p)

    dbConfig
      .db
      .run(
        query.to[List].result.map(EventEntityDataMapper.transformCollection)
      )
  }

  override def findAll(): Future[List[Event]] =
    dbConfig
      .db
      .run(
        Tables.Event.to[List].result.map(_.map(EventEntityDataMapper.transform))
      )

  private def keywordQuery(keyword: Option[String])
  : Query[Tables.Event, Tables.EventRow, Seq] = {
    Tables.Event.filter(event =>
      LiteralColumn(keyword).isEmpty
        || (event.title like LiteralColumn(keyword.map(s => s"%$s%")))
        || (event.description like LiteralColumn(keyword.map(s => s"%$s%")))
    )
  }

  private def termsQuery(termStart: DateTime, termEnd: Option[DateTime])
  : Query[Tables.EventSchedule, Tables.EventScheduleRow, Seq] = {
    val formatted = termEnd.map(value => new Timestamp(value.getMillis))
    Tables.EventSchedule
      .filter(schedule => schedule.stateTime >= new Timestamp(termStart.getMillis))
      .filter(schedule =>
        LiteralColumn(formatted).isEmpty
          || schedule.endTime <= LiteralColumn(formatted)
      )
  }

  override def countNumberOfEvents: Future[Int] =
    dbConfig.db.run(
      Tables.Event.length.result
    )

  override def createEvent(title: String,
                           description: String,
                           status: EventStatus,
                           location: EventLocation): Future[Event] = {
    val command = for {
      id <- Tables.Event.map(
        e => (e.title, e.description, e.statusId, e.locationId)
      ) returning Tables.Event.map(_.id) += (title, description, status.id.toByte, location.id.toByte)
      event <- Tables.Event.filter(_.id === id).result.head.map(EventEntityDataMapper.transform)
    } yield event
    dbConfig.db.run(command)
  }
}
