package net.craftal.core.data.store

import javax.inject.Inject
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.ArtistEntityDataMapper
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class ArtistDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends ArtistRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findArtist(id: Int): Future[Artist] =
    dbConfig.db
      .run(Tables.User.filter(_.id === id).result.head.map(ArtistEntityDataMapper.transform))

  override def findArtistsByEventId(eventId: Int, keyword: Option[String]): Future[List[Artist]] = {
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

  override def findArtistsByGenreId(genreId: Int, keyword: Option[String]): Future[List[Artist]] = {
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

  override def findArtistsByKeyword(keyword: Option[String]): Future[List[Artist]] = {
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

  override def findAll(): Future[List[Artist]] =
    dbConfig
      .db
      .run(
        Tables.User.to[List].result.map(_.map(ArtistEntityDataMapper.transform))
      )
}

