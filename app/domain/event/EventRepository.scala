package domain.event

import java.util.Date

import scala.concurrent.Future

trait EventRepository {

  def find(id: Int): Future[Event] //TODO(RyosukeMurai): should i use UserId class?

  def findByPeriod(startDate: Date, endDate: Date = null): Future[List[Event]]

  def all(): Future[List[Event]]
}
