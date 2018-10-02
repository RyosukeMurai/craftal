package domain.photo.interactor

import domain.photo.{Photo, PhotoRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetPhoto @Inject()(repository: PhotoRepository) extends Interactor {

  def execute(photoId: Int): Future[Photo] = this.repository.find(photoId)
}
