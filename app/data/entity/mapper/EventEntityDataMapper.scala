package data.entity.mapper

import data.entity.Tables.EventRow
import data.entity.Tables.EventScheduleRow
import domain.event._

object EventEntityDataMapper {
  def transform(eventEntity: EventRow): Event =
    new Event(
      id = eventEntity.id,
      title = eventEntity.title,
      description = eventEntity.description,
      status = EventStatus.apply(eventEntity.statusId),
      location = EventLocation.apply(eventEntity.locationId)
    )

  def transform(eventEntity: EventRow, eventScheduleCollection: List[EventScheduleRow]): Event =
    new Event(
      id = eventEntity.id,
      title = eventEntity.title,
      description = eventEntity.description,
      status = EventStatus.apply(eventEntity.statusId),
      location = EventLocation.apply(eventEntity.locationId),
      eventScheduleCollection.map(schedule => EventSchedule(
        eventId = schedule.eventId,
        mapCoordinate = EventMapCoordinate(latitude = 0, longitude = 0),
        venue = schedule.venue,
        stateTime = schedule.stateTime,
        endTime = schedule.endTime
      ))
    )

  def transformCollection(eventRows: List[(EventRow, EventScheduleRow)]): List[Event] =
    eventRows
      .groupBy(_._1.id)
      .map(m => this.transform(m._2.head._1, m._2.map(_._2)))
      .toList

}
