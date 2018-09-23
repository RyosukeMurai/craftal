package presentation.model.event

import java.util.Date

import domain.event.Event

case class EventCalendarDate(date: Date,
                             private val events: List[Event]) {

  def getEvents: List[Event] = this.events.toList
}
