package net.craftal.core.usecase.photo

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.photo.{Photo, PhotoRepository}

import scala.concurrent.Future

@Singleton
class GetPhotos @Inject()(repository: PhotoRepository) extends Interactor {

  def execute(idList: List[Int]): Future[List[Photo]] = this.repository.findPhotoByIdList(idList)
}
