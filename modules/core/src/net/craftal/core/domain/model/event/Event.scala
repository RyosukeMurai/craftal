package net.craftal.core.domain.model.event

import net.craftal.common.domain.model.Entity
import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventStatus.EventStatus

case class Event(id: Int,
                 var title: String,
                 var description: String,
                 var status: EventStatus,
                 var location: EventLocation,
                 schedule: Seq[EventSchedule] = List(),
                 photos: Seq[EventPhoto] = List()) extends Entity[Event] {

  def mainPhotoId: Int = {
    if (this.photos.isEmpty) {
      throw new IllegalAccessException(s"Event ($this.title) doesn't have any photos.")
    }
    this.photos.sortWith(_.positionNo < _.positionNo).head.photoId
  }
}