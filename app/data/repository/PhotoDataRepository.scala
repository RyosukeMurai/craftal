package data.repository

import data.entity.Tables
import data.entity.mapper.PhotoEntityDataMapper
import domain.photo.{Photo, PhotoRepository}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PhotoDataRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
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

