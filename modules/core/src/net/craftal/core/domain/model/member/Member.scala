package net.craftal.core.domain.model.member

import net.craftal.common.domain.model.Entity

case class Member(id: Int,
                  var name: String,
                  var email: String,
                  var prefectureId: Int,
                  photos: List[MemberPhoto] = List()) extends Entity[Member] {

  def iconPhotoId: Int = {
    if (this.photos.isEmpty) {
      throw new IllegalAccessException(s"Member ($this.name) doesn't have any photos.")
    }
    this.photos.sortWith(_.positionNo < _.positionNo).head.photoId
  }
}
