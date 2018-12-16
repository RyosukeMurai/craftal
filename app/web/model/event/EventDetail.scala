package web.model.event

import domain.model.artist.Artist
import domain.model.event.EventLocation.EventLocation
import domain.model.event.EventSchedule
import domain.model.event.EventStatus.EventStatus
import domain.model.photo.Photo

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
