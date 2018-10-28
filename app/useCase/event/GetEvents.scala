package useCase.event

import domain.model.event.{Event, EventRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetEvents @Inject()(repository: EventRepository) extends Interactor {

  def execute(): Future[List[Event]] = this.repository.all()
}
