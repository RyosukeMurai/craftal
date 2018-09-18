package data.entity.mapper

import data.entity.Tables
import domain.event.{Event, EventLocation, EventStatus}

object EventEntityDataMapper {
  def transform(eventEntity: Tables.EventRow): Event =
    new Event(
      id = eventEntity.id,
      title = eventEntity.title,
      description = eventEntity.description,
      status = EventStatus.apply(eventEntity.statusId),
      location = EventLocation.apply(eventEntity.locationId),
      createdAt = eventEntity.createdAt,
      updatedAt = eventEntity.updatedAt
    )
}
