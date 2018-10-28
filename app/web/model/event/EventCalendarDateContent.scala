package web.model.event

import domain.model.event.Event
import domain.model.photo.Photo
import web.service.{ResourceType, URIService}

case class EventCalendarDateContent(private val event: Event,
                                    private val photos: List[Photo]) {

  def getEvent: Event = this.event

  def getMainPhotoUrl: String = {
    val photo = this.photos.find(_.id == this.event.mainPhotoId)
    photo match {
      case Some(p) => URIService.resolveUrlByIdentifierAndResourceType(p.identifier, ResourceType.photo).toString
      case _ => throw new IllegalArgumentException("photoId is not included in holding list")
    }
  }
}
