package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.event.Event
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}


class GetEvents @Inject()(domainService: DomainService)(implicit ec: ExecutionContext) extends Interactor {

  def execute(implicit request: Request[AnyContent], messages: Messages): Future[(List[Event], Int)] =
    for {
      e <- this.domainService.getEvents
      c <- this.domainService.countNumberOfEvents
    } yield (e, c)
}
