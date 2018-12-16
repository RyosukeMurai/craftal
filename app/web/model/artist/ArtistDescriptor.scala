package web.model.artist

import domain.model.artist.Artist
import domain.model.genre.Genre
import domain.model.photo.Photo

class ArtistDescriptor(id: Int,
                       name: String,
                       email: String,
                       genre: Genre,
                       photos: Seq[Photo])
  extends Artist(
    id,
    name,
    email,
    genre
  ) {
}
