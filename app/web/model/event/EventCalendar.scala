package web.model.event

case class EventCalendar(private val days: List[EventCalendarDate]) {

  def getDays: List[EventCalendarDate] = this.days.toList
}
