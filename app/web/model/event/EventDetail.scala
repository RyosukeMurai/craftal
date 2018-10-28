package web.model.event

import domain.model.artist.Artist
import domain.model.event.EventLocation.EventLocation
import domain.model.event.EventStatus.EventStatus
import domain.model.event.{Event, EventSchedule}

class EventDetail(id: Int,
                  title: String,
                  description: String,
                  status: EventStatus,
                  location: EventLocation,
                  schedule: List[EventSchedule] = List(),
                  private val artists: List[Artist] = List()
                 )
  extends Event(
    id,
    title,
    description,
    status,
    location,
    schedule
  ) {

  def getArtists: List[Artist] = this.artists
}
