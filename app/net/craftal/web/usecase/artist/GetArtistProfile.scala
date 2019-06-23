package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetArtistProfile @Inject()(domainService: DomainService)
                                (implicit executionContext: ExecutionContext) extends Interactor {

  def execute(artistId: Int)
             (implicit request: Request[AnyContent], messages: Messages): Future[(Artist, List[Photo], Prefecture, List[Attribute])] =
    for {
      a <- this.domainService.getArtist(artistId)
      p <- this.domainService.getPhotos(a.photos.map(_.photoId))
      pr <- this.domainService.getPrefecture(a.prefectureId)
      at <- this.domainService.getAttributes(a.attributes.map(_.attributeId))
    } yield (a, p, pr, at)
}