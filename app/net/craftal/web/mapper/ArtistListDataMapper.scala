package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.photo.Photo
import net.craftal.web.model.artist.ArtistDescriptor


object ArtistListDataMapper extends DataMapper {

  def transform(artistCollection: List[Artist], photoCollection: List[Photo]): Seq[ArtistDescriptor] =
    artistCollection.map { a =>
      ArtistDescriptor(a.id, a.name, a.email, photoCollection.filter(p => a.photos.map(_.photoId).contains(p.id)))
    }
}
