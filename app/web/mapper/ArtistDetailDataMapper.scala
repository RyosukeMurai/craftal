package web.mapper

import domain.model.artist.Artist
import domain.model.event.Event
import web.model.artist.ArtistDetail


object ArtistDetailDataMapper {
  def transform(artist: Artist, events: List[Event]): ArtistDetail =
    new ArtistDetail(artist.id, artist.name, artist.email, artist.getGenre, events)
}
