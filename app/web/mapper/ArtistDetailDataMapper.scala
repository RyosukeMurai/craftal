package web.mapper

import domain.model.artist.Artist
import domain.model.event.Event
import domain.model.photo.Photo
import web.model.artist.ArtistDetail


object ArtistDetailDataMapper {
  def transform(artist: Artist,
                photos: Seq[Photo],
                events: Seq[Event]): ArtistDetail =
    new ArtistDetail(
      artist.id, artist.name, artist.email, artist.getGenre, photos, events
    )
}
