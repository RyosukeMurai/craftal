package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetArtist @Inject()(domainService: DomainService)(implicit ex: ExecutionContext)  extends Interactor {

  def execute(artistId: Int)
             (implicit request: Request[AnyContent], messages: Messages): Future[(Artist, Genre)] =
    for {
      a <- this.domainService.getArtist(artistId)
      g <- this.domainService.getGenre(a.genreId)
    } yield (a, g)
}
