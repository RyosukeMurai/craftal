package data.store

import data.Tables
import domain.model.photo.{Photo, PhotoRepository}
import data.mapper.PhotoEntityDataMapper
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PhotoDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends PhotoRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def find(id: Int): Future[Photo] =
    dbConfig.db
      .run(Tables.Photo.filter(_.id === id).result.head.map(PhotoEntityDataMapper.transform))

  override def findByIdList(idList: List[Int]): Future[List[Photo]] =
    dbConfig.db
      .run(
        Tables.Photo.filter(_.id.inSet(idList)).to[List].result.map(PhotoEntityDataMapper.transformCollection)
      )

}

