package net.craftal.web.model.event

import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.core.domain.model.event.{Event, EventSchedule}
import net.craftal.core.domain.model.photo.Photo

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
