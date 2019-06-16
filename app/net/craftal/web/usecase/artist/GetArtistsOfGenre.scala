package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetArtistsOfGenre @Inject()(domainService: DomainService)(implicit ex: ExecutionContext) extends Interactor {

  def execute(genreId: Int, keyword: Option[String])
             (implicit request: Request[AnyContent], messages: Messages): Future[(List[Artist], List[Photo], List[Prefecture])] =
    for {
      a <- this.domainService.getArtistsOfGenre(genreId, keyword)
      p <- this.domainService.getPhotos(a.flatMap(_.photos.map(_.photoId)))
      pr <- this.domainService.getPrefectures(a.map(_.prefectureId))
    } yield (a, p, pr)
}