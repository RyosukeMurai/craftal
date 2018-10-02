package data.repository

import data.entity.Tables
import data.entity.mapper.ArtistEntityDataMapper
import domain.artist.{Artist, ArtistRepository}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ArtistDataRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends ArtistRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def find(id: Int): Future[Artist] =
    dbConfig.db
      .run(Tables.User.filter(_.id === id).result.head.map(ArtistEntityDataMapper.transform))

  override def findArtistsByEventId(eventId: Int): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User
      if (for {
        a <- Tables.EventArtist if u.id === a.userId && a.eventId === eventId
      } yield a.userId).exists
    } yield u
    dbConfig
      .db
      .run(
        query.to[List].result.map(_.map(ArtistEntityDataMapper.transform))
      )
  }
}

