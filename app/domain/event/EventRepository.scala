package domain.event

import java.util.Date

import scala.concurrent.Future

trait EventRepository {

  def find(id: Int): Future[Event] //TODO(RyosukeMurai): should i use UserId class?

  def findByTerm(termStart: Date, termEnd: Option[Date]): Future[List[Event]]

  def all(): Future[List[Event]]
}
