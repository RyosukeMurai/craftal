package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetEvent @Inject()(domainService: DomainService)
                        (implicit executionContext: ExecutionContext) extends Interactor {

  def execute(eventId: Int)
             (implicit request: Request[AnyContent], messages: Messages): Future[(Event, List[Photo], List[Prefecture], List[Artist], List[Attribute])] =
    for {
      e <- this.domainService.getEvent(eventId)
      a <- this.domainService.getArtistsParticipatingInEvent(eventId, None)
      p <- this.domainService.getPhotos(e.photos.map(_.photoId).toList ++ a.flatMap(_.photos).map(_.photoId))
      pr <- this.domainService.getPrefectures(e.schedule.map(_.prefectureId).toList ++ a.map(_.prefectureId))
      at <- this.domainService.getAttributes(e.attributes.map(_.attributeId).toList)
    } yield (e, p, pr, a, at)
}