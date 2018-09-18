package domain.event

import scala.concurrent.Future

trait EventRepository {

  def find(id: Int): Future[Event] //TODO(RyosukeMurai): should i use UserId class?

  def all(): Future[List[Event]]
}
