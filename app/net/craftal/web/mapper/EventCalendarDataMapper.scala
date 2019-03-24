package net.craftal.web.mapper

import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.web.model.event.EventCalendar


object EventCalendarDataMapper {

  def transform(eventCollection: List[Event], photoCollection: List[Photo]): EventCalendar =
    EventCalendar(eventCollection
      .flatMap(_.schedule)
      .map(_.stateTime)
      .distinct
      .map(st => st -> eventCollection.filter(_.schedule.exists(_.stateTime.equals(st))))
      .toMap
    )
}
