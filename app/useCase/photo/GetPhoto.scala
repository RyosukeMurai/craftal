package useCase.photo

import domain.model.photo.{Photo, PhotoRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetPhoto @Inject()(repository: PhotoRepository) extends Interactor {

  def execute(photoId: Int): Future[Photo] = this.repository.find(photoId)
}
