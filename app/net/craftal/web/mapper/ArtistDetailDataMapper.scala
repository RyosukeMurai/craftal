package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.web.model.artist.ArtistDetail


object ArtistDetailDataMapper {
  def transform(artist: Artist,
                genre: Genre,
                photos: Seq[Photo],
                events: Seq[Event]): ArtistDetail =
    new ArtistDetail(
      artist.id, artist.name, artist.email, genre, photos, events
    )
}
