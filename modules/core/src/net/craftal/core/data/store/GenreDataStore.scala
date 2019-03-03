package net.craftal.core.data.store

import javax.inject.{Inject, Singleton}
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.GenreEntityDataMapper
import net.craftal.core.domain.model.genre.{Genre, GenreRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class GenreDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends GenreRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findGenre(genreId: Int): Future[Genre] = {
    val query = for {
      (g, p) <- Tables.Genre.filter(_.id === genreId) joinLeft Tables.Genre on (_.parentId === _.id)
    } yield (g, p)

    dbConfig
      .db
      .run(
        query.result.head.map(GenreEntityDataMapper.transform)
      )
  }

  override def findAll(): Future[List[Genre]] = {
    val query = for {
      (g, p) <- Tables.Genre joinLeft Tables.Genre on (_.parentId === _.id)
    } yield (g, p)

    dbConfig
      .db
      .run(
        query.to[List].result.map(GenreEntityDataMapper.transformCollection)
      )
  }
}

