package web.model.event

import java.util.Date

case class EventCalendarDate(date: Date,
                             private val events: List[EventCalendarDateContent]) {

  def getEvents: List[EventCalendarDateContent] = this.events.toList
}
