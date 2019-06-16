package net.craftal.core.domain.model.artist

import net.craftal.common.domain.model.Entity

case class Artist(id: Int,
                  var name: String,
                  var email: String,
                  var genreId: Int,
                  var prefectureId: Int,
                  var aboutInquiry: String,
                  var homePageUrl: String,
                  var shopPageUrl: String,
                  var twitterUrl: String,
                  var facebookUrl: String,
                  var instagramUrl: String,
                  var selfIntroduction: String,
                  photos: List[ArtistPhoto] = List(),
                  attributes: List[ArtistAttribute] = List()) extends Entity[Artist] {

  def iconPhotoId: Int = {
    if (this.photos.isEmpty) {
      throw new IllegalAccessException(s"Member ($this.name) doesn't have any photos.")
    }
    this.photos.sortWith(_.positionNo < _.positionNo).head.photoId
  }

  def coverPhotoId: Int = {
    if (this.photos.isEmpty) {
      throw new IllegalAccessException(s"Member ($this.name) doesn't have any photos.")
    }
    this.photos
      .filter(p => p.photoId != this.iconPhotoId)
      .sortWith(_.positionNo < _.positionNo)
      .head
      .photoId
  }
}
