package domain.photo.interactor

import domain.photo.{Photo, PhotoRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetPhotosByIdList @Inject()(repository: PhotoRepository) extends Interactor {

  def execute(idList: List[Int]): Future[List[Photo]] = this.repository.findByIdList(idList)
}
