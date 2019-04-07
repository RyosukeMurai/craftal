package net.craftal.web.model.event

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventSchedule
import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.core.domain.model.photo.Photo

class EventDetail(id: Int,
                  title: String,
                  description: String,
                  status: EventStatus,
                  location: EventLocation,
                  schedule: Seq[EventSchedule] = List(),
                  photos: Seq[Photo] = List(),
                  val artists: Seq[Artist] = List()
                 )
  extends EventDescriptor(
    id,
    title,
    description,
    status,
    location,
    schedule,
    photos
  ) {
}
