package web.model.artist

import domain.model.artist.Artist
import domain.model.photo.Photo
import web.service.{ResourceType, URIService}

case class ArtistGenreSummaryContent(private val artist: Artist,
                                     private val photos: List[Photo]) {

  def getArtist: Artist = this.artist

  def getIconPhotoUrl: String = {
    val photo = this.photos.find(_.id == this.artist.iconPhotoId)
    photo match {
      case Some(p) => URIService.resolveUrlByIdentifierAndResourceType(p.identifier, ResourceType.photo).toString
      case _ => throw new IllegalArgumentException("photoId is not included in holding list")
    }
  }
}
