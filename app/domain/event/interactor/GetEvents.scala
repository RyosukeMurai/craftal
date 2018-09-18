package domain.event.interactor

import domain.event.{Event, EventRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetEvents @Inject()(repository: EventRepository) extends Interactor {

  def execute(): Future[List[Event]] = this.repository.all()
}
