package net.craftal.core.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.core.domain.model.event.{Event, EventRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class CreateEvent @Inject()(eventRepository: EventRepository)
                           (implicit ex: ExecutionContext) extends Interactor {
  def execute(title: String,
              description: String,
              status: EventStatus,
              location: EventLocation): Future[Event] =
    this.eventRepository.createEvent(
      title = title,
      description = description,
      status = status,
      location = location
    )
}
