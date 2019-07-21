package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.event.Event
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class UpdateEvent @Inject()(domainService: DomainService)
                           (implicit ex: ExecutionContext) extends Interactor {

  def execute(eventId: Int,
              title: Option[String],
              description: Option[String])
             (implicit request: Request[AnyContent], messages: Messages): Future[Event] =
    this.domainService.updateEvent(
      eventId,
      title,
      description
    )
}
