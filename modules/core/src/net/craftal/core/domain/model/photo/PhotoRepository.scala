package net.craftal.core.domain.model.photo

import scala.concurrent.Future

trait PhotoRepository {

  def findPhoto(photoId: Int): Future[Photo]

  def findPhotosByIdList(idList: List[Int]): Future[List[Photo]]
}
