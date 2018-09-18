package data.repository

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

  def all(): Future[List[Event]] =
    dbConfig
      .db
      .run(
        Tables.Event.to[List].result.map(_.map(EventEntityDataMapper.transform))
      )
}
