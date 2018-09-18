package domain.event.interactor

import domain.event.{Event, EventRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetEvent @Inject()(repository: EventRepository) extends Interactor {

  def execute(id: Int): Future[Event] = this.repository.find(id)
}
