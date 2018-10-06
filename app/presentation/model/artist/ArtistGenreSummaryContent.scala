package presentation.model.artist

import application.{ResourceType, URIService}
import domain.artist.Artist
import domain.photo.Photo

case class ArtistGenreSummaryContent(private val artist: Artist,
                                     private val photos: List[Photo]) {

  def getArtist: Artist = this.artist

  def getIconPhotoUrl: String = {
    println(artist.iconPhotoId)
    val photo = this.photos.find(_.id == this.artist.iconPhotoId)
    photo match {
      case Some(p) => URIService.resolveUrlByIdentifierAndResourceType(p.identifier, ResourceType.photo).toString
      case _ => throw new IllegalArgumentException("photoId is not included in holding list")
    }
  }
}
