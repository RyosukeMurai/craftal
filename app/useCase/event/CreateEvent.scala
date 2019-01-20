package useCase.event

import javax.inject._

import domain.model.event.{Event, EventLocation, EventRepository, EventStatus}
import useCase.Interactor
import useCase.event.request.CreateEventRequest

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class CreateEvent @Inject()(eventRepository: EventRepository)
                           (implicit ex: ExecutionContext) extends Interactor {
  def execute(request: CreateEventRequest): Future[Event] =
  //TODO(RyosukeMurai): temporary code
    this.eventRepository.createEvent(
      title = request.title,
      description = request.description,
      status = EventStatus.apply(request.statusId),
      location = EventLocation.apply(request.locationId)
    )
}
