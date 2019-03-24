package net.craftal.core.usecase.photo

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.photo.{Photo, PhotoRepository}

import scala.concurrent.Future

class GetPhoto @Inject()(repository: PhotoRepository) extends Interactor {

  def execute(photoId: Int): Future[Photo] = this.repository.findPhoto(photoId)
}
