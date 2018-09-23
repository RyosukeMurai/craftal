package presentation.mapper

import domain.event._
import presentation.model.event.EventCalendarDate


object EventCalendarDataMapper {

  def transform(eventCollection: List[Event]): List[EventCalendarDate] =
    eventCollection
      .flatMap(_.getSchedule)
      .map(_.stateTime)
      .map(st => EventCalendarDate(st, eventCollection.filter(_.getSchedule.exists(_.stateTime.equals(st)))))
}
