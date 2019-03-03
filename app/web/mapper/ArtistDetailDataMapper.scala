package web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import web.model.artist.ArtistDetail


object ArtistDetailDataMapper {
  def transform(artist: Artist,
                photos: Seq[Photo],
                events: Seq[Event]): ArtistDetail =
    new ArtistDetail(
      artist.id, artist.name, artist.email, artist.getGenreId, photos, events
    )
}
