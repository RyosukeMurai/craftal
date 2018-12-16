package web.mapper

import domain.model.artist._
import domain.model.photo.Photo
import web.model.artist._


object ArtistListDataMapper {

  def transform(artistCollection: List[Artist], photoCollection: List[Photo]): Seq[ArtistDescriptor] =
    artistCollection.map { a =>
      new ArtistDescriptor(a.id, a.name, a.email, a.getGenre,
        photoCollection.filter(p => a.getPhotos.map(_.photoId).contains(p.id))
      )
    }
}
