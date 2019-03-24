package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.Future


class GetArtists @Inject()(domainService: DomainService) extends Interactor {

  def execute(keyword: Option[String])
             (implicit request: Request[AnyContent], messages: Messages): Future[(List[Artist], List[Genre])] =
    for {
      a <- this.domainService.getArtists(keyword)
      g <- this.domainService.getGenres(a.map(_.genreId))
    } yield (a, g)
}
