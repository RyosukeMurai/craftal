package web.model.event

import java.util.Date

import net.craftal.core.domain.model.event.Event

case class EventCalendar(events: Map[Date, Seq[Event]]) {

  /*
  def getMainPhotoUrl: String = {
    val photo = this.photos.findArtist(_.id == this.event.mainPhotoId)
    photo match {
      case Some(p) => URIService.resolveUrlByIdentifierAndResourceType(p.identifier, ResourceType.photo).toString
      case _ => throw new IllegalArgumentException("photoId is not included in holding list")
    }
  }
  */
}
