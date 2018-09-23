package data.repository

import java.util.Date
import java.sql.Timestamp

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

  def find(id: Int): Future[Event] =
    dbConfig.db.run(
      Tables.Event.filter(_.id === id).result.head.map(EventEntityDataMapper.transform)
    )

  def findByTerm(termStart: Date, termEnd: Option[Date]): Future[List[Event]] = {
    // Guard
    termEnd match {
      case Some(x) if x.before(termStart) =>
        throw new IllegalArgumentException("termStart must be earlier than termEnd.")
      case _ =>
    }

    var criteriaTerms = Tables.EventSchedule.filter(_.stateTime >= new Timestamp(termStart.getTime))

    termEnd match {
      case Some(x) =>
        criteriaTerms = criteriaTerms.filter(_.endTime <= new Timestamp(x.getTime))
      case _ =>
    }

    val query = for {
      e <- Tables.Event
      s <- criteriaTerms
      if e.id === s.eventId
    } yield (e, s)

    dbConfig
      .db
      .run(
        query.to[List].result.map(EventEntityDataMapper.transformCollection)
      )
  }

  def all(): Future[List[Event]] =
    dbConfig
      .db
      .run(
        Tables.Event.to[List].result.map(_.map(EventEntityDataMapper.transform))
      )
}
