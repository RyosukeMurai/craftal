package web.model.artist

import domain.model.event.Event
import domain.model.genre.Genre
import domain.model.photo.Photo

class ArtistDetail(id: Int,
                   name: String,
                   email: String,
                   genre: Genre,
                   photos: Seq[Photo],
                   events: Seq[Event])
  extends ArtistDescriptor(
    id,
    name,
    email,
    genre,
    photos
  ) {

  def getEventsParticipateInThePresent: Seq[Event] = this.events

  def getEventsParticipatedInThePast: Seq[Event] = this.events
}
