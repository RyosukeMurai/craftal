package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import org.joda.time.DateTime
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetEventsInTerm @Inject()(domainService: DomainService)
                               (implicit executionContext: ExecutionContext) extends Interactor {

  def execute(termStart: DateTime,
              termEnd: Option[DateTime],
              keyword: Option[String])
             (implicit request: Request[AnyContent], messages: Messages): Future[(List[Event], List[Photo], List[Prefecture])] =
    for {
      e <- this.domainService.getEvents(termStart, termEnd, keyword)
      p <- this.domainService.getPhotos(e.flatMap(_.photos.map(_.photoId)))
      pr <- this.domainService.getPrefectures(e.flatMap(_.schedule.map(_.prefectureId)))
    } yield (e, p, pr)
}
