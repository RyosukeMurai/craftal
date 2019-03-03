package web.mapper

import domain.model.event._
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import web.model.event.EventCalendar


object EventCalendarDataMapper {

  def transform(eventCollection: List[Event], photoCollection: List[Photo]): EventCalendar =
    EventCalendar(eventCollection
      .flatMap(_.getSchedule)
      .map(_.stateTime)
      .distinct
      .map(st => st -> eventCollection.filter(_.getSchedule.exists(_.stateTime.equals(st))))
      .toMap
    )
}
