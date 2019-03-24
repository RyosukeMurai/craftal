package net.craftal.web.model.artist

import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo

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
