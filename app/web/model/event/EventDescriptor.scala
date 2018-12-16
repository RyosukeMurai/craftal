package web.model.event

import domain.model.event.EventLocation.EventLocation
import domain.model.event.EventStatus.EventStatus
import domain.model.event.{Event, EventSchedule}
import domain.model.photo.Photo

class EventDescriptor(id: Int,
                      title: String,
                      description: String,
                      status: EventStatus,
                      location: EventLocation,
                      schedule: Seq[EventSchedule] = List(),
                      photos: Seq[Photo] = List())
  extends Event(
    id,
    title,
    description,
    status,
    location,
    schedule
  ) {
}
