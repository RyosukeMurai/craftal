package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}


class GetArtists @Inject()(domainService: DomainService)(implicit ex: ExecutionContext) extends Interactor {

  def execute(keyword: Option[String])
             (implicit request: Request[AnyContent],
              messages: Messages): Future[(List[Artist], List[Photo], List[Genre], List[Prefecture])] =
    for {
      a <- this.domainService.getArtists(keyword)
      p <- this.domainService.getPhotos(a.flatMap(_.photos.map(_.photoId)))
      g <- this.domainService.getGenres(a.map(_.genreId))
      pr <- this.domainService.getPrefectures(a.map(_.prefectureId))
    } yield (a, p, g, pr)
}
