package useCase.event

import domain.model.event.{Event, EventRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetEvent @Inject()(repository: EventRepository) extends Interactor {

  def execute(id: Int): Future[Event] = this.repository.find(id)
}
