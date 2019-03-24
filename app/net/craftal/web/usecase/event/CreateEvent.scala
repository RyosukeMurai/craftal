package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.event.{Event, EventLocation, EventStatus}
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class CreateEvent @Inject()(domainService: DomainService)
                           (implicit ex: ExecutionContext) extends Interactor {

  def execute(title: String,
              description: String,
              status: Int,
              location: Int)
             (implicit request: Request[AnyContent], messages: Messages): Future[Event] =
    this.domainService.createEvent(
      title,
      description,
      EventStatus(status),
      EventLocation(location)
    )
}
