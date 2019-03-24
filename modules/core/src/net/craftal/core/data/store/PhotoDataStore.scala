package net.craftal.core.data.store

import javax.inject.{Inject, Singleton}
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.PhotoEntityDataMapper
import net.craftal.core.domain.model.photo.{Photo, PhotoRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class PhotoDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends PhotoRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findPhoto(photoId: Int): Future[Photo] =
    dbConfig.db
      .run(Tables.Photo.filter(_.id === photoId).result.head.map(PhotoEntityDataMapper.transform))

  override def findPhotosByIdList(idList: List[Int]): Future[List[Photo]] =
    dbConfig.db
      .run(
        Tables.Photo.filter(_.id.inSet(idList)).to[List].result.map(PhotoEntityDataMapper.transformCollection)
      )

}

