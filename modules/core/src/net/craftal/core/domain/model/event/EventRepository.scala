package net.craftal.core.domain.model.event

import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventStatus.EventStatus
import org.joda.time.DateTime

import scala.concurrent.Future

trait EventRepository {

  def findEvent(eventId: Int): Future[Event]

  def findEventsByKeyword(keyword: String): Future[List[Event]]

  def findEventsByTerm(termStart: DateTime, termEnd: Option[DateTime], keyword: Option[String]): Future[List[Event]]

  def findAll(): Future[List[Event]]

  def createEvent(title: String,
                  description: String,
                  status: EventStatus,
                  location: EventLocation): Future[Event]

  def countNumberOfEvents: Future[Int]

}
