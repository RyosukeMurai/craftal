package net.craftal.web.usecase.member

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}


class GetMypage @Inject()(domainService: DomainService)(implicit ex: ExecutionContext) extends Interactor {

  def execute(memberId: Int)
             (implicit request: Request[AnyContent],
              messages: Messages): Future[(List[Artist], List[Event], List[Photo], List[Genre], List[Prefecture])] =
    for {
      a <- this.domainService.getFollowingArtists(memberId)
      e <- this.domainService.getFollowingEvents(memberId)
      p <- this.domainService.getPhotos(e.flatMap(_.photos.map(_.photoId)) ++ a.flatMap(_.photos.map(_.photoId)))
      g <- this.domainService.getGenres(a.map(_.genreId))
      pr <- this.domainService.getPrefectures(a.map(_.prefectureId) ++ e.flatMap(_.schedule.map(_.prefectureId).toList))
    } yield (a, e, p, g, pr)
}
