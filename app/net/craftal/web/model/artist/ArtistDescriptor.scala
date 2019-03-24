package net.craftal.web.model.artist

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo

class ArtistDescriptor(id: Int,
                       name: String,
                       email: String,
                       genre: Genre,
                       photos: Seq[Photo])
  extends Artist(
    id,
    name,
    email,
    genre.id
  ) {
}
