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

  override def findArtist(id: Int): Future[Artist] = {
    val query = for {
      u <- Tables.User if u.id === id
      a <- Tables.Artist if u.id === a.userId
      p <- Tables.ArtistPhoto if u.id === p.artistId
      at <- Tables.ArtistAttribute if u.id === at.artistId
    } yield (u, a, p, at)

    dbConfig.db
      .run(query.to[List].result.map(ArtistEntityDataMapper.transformCollection(_).head))
  }

  override def findArtistsByEventId(eventId: Int, keyword: Option[String]): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User if (for {
        a <- Tables.EventArtist if u.id === a.artistId && a.eventId === eventId
      } yield a.artistId).exists
      a <- Tables.Artist if u.id === a.userId
      p <- Tables.ArtistPhoto if u.id === p.artistId
      at <- Tables.ArtistAttribute if u.id === at.artistId
    } yield (u, a, p, at)
    dbConfig
      .db
      .run(
        query.to[List].result.map(ArtistEntityDataMapper.transformCollection)
      )
  }

  override def findArtistsByGenreId(genreId: Int, keyword: Option[String]): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User
      a <- Tables.Artist if u.id === a.userId && a.genreId === genreId
      p <- Tables.ArtistPhoto if u.id === p.artistId
      at <- Tables.ArtistAttribute if u.id === at.artistId
    } yield (u, a, p, at)

    dbConfig
      .db
      .run(
        query.to[List].result.map(ArtistEntityDataMapper.transformCollection)
      )
  }

  override def findArtistsByFollowerId(followerId: Int): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User if (for {
        f <- Tables.ArtistFollower if u.id === f.artistId && f.followerId === followerId
      } yield f.artistId).exists
      a <- Tables.Artist if u.id === a.userId
      p <- Tables.ArtistPhoto if u.id === p.artistId
      at <- Tables.ArtistAttribute if u.id === at.artistId
    } yield (u, a, p, at)
    dbConfig
      .db
      .run(
        query.to[List].result.map(ArtistEntityDataMapper.transformCollection)
      )
  }

  override def findArtistsByKeyword(keyword: Option[String]): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User
      a <- Tables.Artist if u.id === a.userId
      p <- Tables.ArtistPhoto if u.id === p.artistId
      at <- Tables.ArtistAttribute if u.id === at.artistId
    } yield (u, a, p, at)

    dbConfig
      .db
      .run(
        query.to[List].result.map(ArtistEntityDataMapper.transformCollection)
      )
  }

  override def findAll(): Future[List[Artist]] = {
    val query = for {
      u <- Tables.User
      a <- Tables.Artist if u.id === a.userId
      p <- Tables.ArtistPhoto if u.id === p.artistId
      at <- Tables.ArtistAttribute if u.id === at.artistId
    } yield (u, a, p, at)

    dbConfig
      .db
      .run(
        query.to[List].result.map(ArtistEntityDataMapper.transformCollection)
      )
  }
}

