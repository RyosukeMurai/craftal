package web.model.artist

import domain.model.artist.Artist
import domain.model.event.Event
import domain.model.genre.Genre

class ArtistDetail(id: Int,
                   name: String,
                   email: String,
                   genre: Genre,
                   private val events: List[Event] = List())
  extends Artist(
    id,
    name,
    email,
    genre
  ) {

  def getArtists: List[Event] = this.events
}
