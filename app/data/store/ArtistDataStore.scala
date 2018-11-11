package data.store

import data.Tables
import domain.model.artist.{Artist, ArtistRepository}
import data.mapper.ArtistEntityDataMapper
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ArtistDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
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

  override def findByGenreId(genreId: Int, keyword: Option[String]): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User
      p <- Tables.ArtistPhoto
      if u.id === p.artistId
    } yield (u, p)

    dbConfig
      .db
      .run(
        query.to[List].result.map(ArtistEntityDataMapper.transformCollection)
      )
  }

  override def findByKeyword(keyword: Option[String]): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User
      p <- Tables.ArtistPhoto
      if u.id === p.artistId
    } yield (u, p)

    dbConfig
      .db
      .run(
        query.to[List].result.map(ArtistEntityDataMapper.transformCollection)
      )
  }

  override def all(): Future[List[Artist]] =
    dbConfig
      .db
      .run(
        Tables.User.to[List].result.map(_.map(ArtistEntityDataMapper.transform))
      )
}

