package domain.model.event

import java.util.Date

import domain.model.event.EventLocation.EventLocation
import domain.model.event.EventStatus.EventStatus

import scala.concurrent.Future

trait EventRepository {

  def find(id: Int): Future[Event] //TODO(RyosukeMurai): should i use UserId class?

  def findByTerm(termStart: Date, termEnd: Option[Date], keyword: Option[String]): Future[List[Event]]

  def findByKeyword(keyword: String): Future[List[Event]]

  def createEvent(title: String,
                  description: String,
                  status: EventStatus,
                  location: EventLocation): Future[Event]

  def countNumberOfEvents: Future[Int]

  def all(): Future[List[Event]]
}
