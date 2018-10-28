package web.model.mapper

import domain.model.event._
import domain.model.photo.Photo
import web.model.event.{EventCalendar, EventCalendarDate, EventCalendarDateContent}


object EventCalendarDataMapper {

  def transform(eventCollection: List[Event], photoCollection: List[Photo]): EventCalendar =
    EventCalendar(eventCollection
      .flatMap(_.getSchedule)
      .map(_.stateTime)
      .map(st => EventCalendarDate(
        st,
        eventCollection
          .filter(_.getSchedule.exists(_.stateTime.equals(st)))
          .map(EventCalendarDateContent(_, photoCollection))
      ))
    )
}
