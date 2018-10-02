package data.entity.mapper

import data.entity.Tables.{EventPhotoRow, EventRow, EventScheduleRow}
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

  def transform(eventEntity: EventRow,
                eventScheduleCollection: List[EventScheduleRow],
                eventPhotoCollection: List[EventPhotoRow]): Event =
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
      )),
      eventPhotoCollection.map(photo => EventPhoto(
        eventId = photo.eventId,
        photoId = photo.photoId,
        positionNo = photo.positionNo
      ))
    )

  def transformCollection(eventRows: List[(EventRow, EventScheduleRow, EventPhotoRow)]): List[Event] =
    eventRows
      .groupBy(_._1.id)
      .map(m => this.transform(m._2.head._1, m._2.map(_._2), m._2.map(_._3)))
      .toList

}
