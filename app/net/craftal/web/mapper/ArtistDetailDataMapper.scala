package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.artist.ArtistDetail


object ArtistDetailDataMapper extends DataMapper {
  def transform(artist: Artist,
                genre: Genre,
                prefecture: Prefecture,
                photos: Seq[Photo],
                events: Seq[Event]): ArtistDetail =
    ArtistDetail(
      artist.id, artist.name, artist.email, genre, photos, events
    )
}
