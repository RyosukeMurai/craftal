package net.craftal.web.usecase.photo

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.photo.Photo

import scala.concurrent.Future

class GetPhoto @Inject()(domainService: DomainService) extends Interactor {

  def execute(photoId: Int): Future[Photo] = this.domainService.getPhoto(photoId)
}
