package presentation.model.artist

import domain.artist.Artist
import domain.event.Event
import domain.genre.Genre

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
