package presentation.model.event

import application.{ResourceType, URIService}
import domain.event.Event
import domain.photo.Photo

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
