package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.event._
import org.joda.time.DateTime

object EventEntityDataMapper {

  def transform(eventEntity: Tables.EventRow): Event =
    Event(
      id = eventEntity.id,
      title = eventEntity.title,
      description = eventEntity.description,
      status = EventStatus.apply(eventEntity.statusId),
      location = EventLocation.apply(eventEntity.locationId)
    )

  def transform(eventEntity: Tables.EventRow,
                eventScheduleCollection: List[Tables.EventScheduleRow],
                eventPhotoCollection: List[Tables.EventPhotoRow]): Event =
    Event(
      id = eventEntity.id,
      title = eventEntity.title,
      description = eventEntity.description,
      status = EventStatus.apply(eventEntity.statusId),
      location = EventLocation.apply(eventEntity.locationId),
      eventScheduleCollection.map(schedule => EventSchedule(
        mapCoordinate = EventMapCoordinate(latitude = 0, longitude = 0),
        venue = schedule.venue,
        stateTime = new DateTime(schedule.stateTime.getTime),
        endTime = new DateTime(schedule.endTime.getTime)
      )),
      eventPhotoCollection.map(photo => EventPhoto(
        photoId = photo.photoId,
        positionNo = photo.positionNo
      ))
    )

  def transformCollection(eventRows: List[(Tables.EventRow, Tables.EventScheduleRow, Tables.EventPhotoRow)]): List[Event] =
    eventRows
      .groupBy(_._1.id)
      .map(m => this.transform(m._2.head._1, m._2.map(_._2), m._2.map(_._3)))
      .toList

}
