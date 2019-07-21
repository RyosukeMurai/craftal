package net.craftal.core.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.{Event, EventRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class UpdateEvent @Inject()(eventRepository: EventRepository)
                           (implicit ex: ExecutionContext) extends Interactor {
  def execute(eventId: Int,
              title: Option[String],
              description: Option[String]): Future[Event] =
    this.eventRepository.updateEvent(
      eventId = eventId,
      title = title,
      description = description
    )
}
