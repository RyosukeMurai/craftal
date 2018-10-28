package useCase.photo

import domain.model.photo.{Photo, PhotoRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetPhotosByIdList @Inject()(repository: PhotoRepository) extends Interactor {

  def execute(idList: List[Int]): Future[List[Photo]] = this.repository.findByIdList(idList)
}
