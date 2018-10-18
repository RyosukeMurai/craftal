package data.repository

import data.entity.Tables
import data.entity.mapper.GenreEntityDataMapper
import domain.genre.{Genre, GenreRepository}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class GenreDataRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends GenreRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def find(id: Int): Future[Genre] = {
    val query = for {
      (g, p) <- Tables.Genre.filter(_.id === id) joinLeft Tables.Genre on (_.parentId === _.id)
    } yield (g, p)

    dbConfig
      .db
      .run(
        query.result.head.map(GenreEntityDataMapper.transform)
      )
  }

  override def all(): Future[List[Genre]] = {
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

