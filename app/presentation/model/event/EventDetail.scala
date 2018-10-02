package presentation.model.event

import domain.artist.Artist
import domain.event.EventLocation.EventLocation
import domain.event.EventStatus.EventStatus
import domain.event.{Event, EventSchedule}

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
