package net.craftal.core.domain.model.eventer

import net.craftal.common.domain.model.Entity

case class Eventer(id: Int,
                   var name: String,
                   var email: String,
                   photos: List[EventerPhoto] = List()) extends Entity[Eventer] {

  def iconPhotoId: Int = {
    if (this.photos.isEmpty) {
      throw new IllegalAccessException(s"Eventer ($this.name) doesn't have any photos.")
    }
    this.photos.sortWith(_.positionNo < _.positionNo).head.photoId
  }
}
