package net.craftal.core.domain.model.artist

import net.craftal.common.domain.model.Entity

case class Artist(id: Int,
                  var name: String,
                  var email: String,
                  var genreId: Int,
                  photos: List[ArtistPhoto] = List()) extends Entity[Artist] {

  def iconPhotoId: Int = {
    if (this.photos.isEmpty) {
      throw new IllegalAccessException(s"Artist ($this.name) doesn't have any photos.")
    }
    this.photos.sortWith(_.positionNo < _.positionNo).head.photoId
  }
}
