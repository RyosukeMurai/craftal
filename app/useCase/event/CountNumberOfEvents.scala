package useCase.event

import domain.model.event.EventRepository
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class CountNumberOfEvents @Inject()(repository: EventRepository) extends Interactor {

  def execute(): Future[Int] = this.repository.countNumberOfEvents
}
