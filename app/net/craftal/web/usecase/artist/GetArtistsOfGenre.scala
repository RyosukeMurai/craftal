package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.photo.Photo
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.Future

class GetArtistsOfGenre @Inject()(domainService: DomainService) extends Interactor {

  def execute(genreId: Int, keyword: Option[String])
             (implicit request: Request[AnyContent], messages: Messages): Future[(List[Artist], List[Photo])] =
    for {
      a <- this.domainService.getArtistsOfGenre(genreId, keyword)
      p <- this.domainService.getPhotos(a.flatMap(_.photos.map(_.photoId)))
    } yield (a, p)
}