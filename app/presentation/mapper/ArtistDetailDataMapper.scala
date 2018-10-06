package presentation.mapper

import domain.artist.Artist
import domain.event.Event
import presentation.model.artist.ArtistDetail


object ArtistDetailDataMapper {
  def transform(artist: Artist, events: List[Event]): ArtistDetail =
    new ArtistDetail(artist.id, artist.name, artist.email, artist.getGenre, events)
}
