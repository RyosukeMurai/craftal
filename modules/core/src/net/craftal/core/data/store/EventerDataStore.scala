package net.craftal.core.data.store

import javax.inject.Inject
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.EventerEntityDataMapper
import net.craftal.core.domain.model.eventer.{Eventer, EventerRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class EventerDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends EventerRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findEventer(id: Int): Future[Eventer] = {
    val query = for {
      u <- Tables.User if u.id === id
    } yield u

    dbConfig.db
      .run(query.to[List].result.map(EventerEntityDataMapper.transformCollection(_).head))
  }

  override def findAll(): Future[List[Eventer]] = {
    val query = for {
      u <- Tables.User
    } yield u

    dbConfig
      .db
      .run(
        query.to[List].result.map(EventerEntityDataMapper.transformCollection)
      )
  }

  override def createEventer(userId: Int): Future[Eventer] = {
    for {
      e <- this.findEventer(userId)
    } yield e
  }

  override def updateEventerProfile(eventerId: Int,
                                    name: Option[String]): Future[Eventer] = {

    for {
      u <- this.findEventer(eventerId)
      _ <- dbConfig.db.run(Tables.User.filter(_.id === eventerId).map(_.name).update(name.getOrElse(u.name)))
      u <- this.findEventer(eventerId)
    } yield u
  }
}

