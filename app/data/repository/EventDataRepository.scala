package data.repository

import java.sql.Timestamp
import java.util.Date

import data.entity.Tables
import data.entity.mapper.EventEntityDataMapper
import domain.event.{Event, EventRepository}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class EventDataRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends EventRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def find(id: Int): Future[Event] =
    dbConfig.db.run(
      Tables.Event.filter(_.id === id).result.head.map(EventEntityDataMapper.transform)
    )

  override def findByTerm(termStart: Date, termEnd: Option[Date], keyword: Option[String]): Future[List[Event]] = {
    // Guard
    termEnd match {
      case Some(x) if x.before(termStart) =>
        throw new IllegalArgumentException("termStart must be earlier than termEnd.")
      case _ =>
    }

    val query = for {
      e <- this.keywordQuery(keyword)
      s <- this.termsQuery(termStart, termEnd)
      if e.id === s.eventId
    } yield (e, s)

    var res = query.to[List].result
    res.statements.foreach(println)

    dbConfig
      .db
      .run(
        query.to[List].result.map(EventEntityDataMapper.transformCollection)
      )
  }

  override def findByKeyword(keyword: String): Future[List[Event]] = {
    val query = for {
      e <- this.keywordQuery(Option(keyword))
      s <- Tables.EventSchedule
      if e.id === s.eventId
    } yield (e, s)

    dbConfig
      .db
      .run(
        query.to[List].result.map(EventEntityDataMapper.transformCollection)
      )
  }

  override def all(): Future[List[Event]] =
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

  private def termsQuery(termStart: Date, termEnd: Option[Date])
  : Query[Tables.EventSchedule, Tables.EventScheduleRow, Seq] = {
    val formatted = termEnd.map(value => new Timestamp(value.getTime))
    Tables.EventSchedule
      .filter(schedule => schedule.stateTime >= new Timestamp(termStart.getTime))
      .filter(schedule =>
        LiteralColumn(formatted).isEmpty
          || schedule.endTime <= LiteralColumn(formatted)
      )
  }
}
