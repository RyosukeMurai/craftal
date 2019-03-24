package net.craftal.web.model.artist

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre

case class ArtistSummary(artists: Map[Genre, Seq[Artist]]) {

  //Should use view helper?
  /*
  def getIconPhotoUrl: String = {
    val photo = this.photos.findArtist(_.id == this.artist.iconPhotoId)
    photo match {
      case Some(p) => URIService.resolveUrlByIdentifierAndResourceType(p.identifier, ResourceType.photo).toString // remove service call from data model
      case _ => throw new IllegalArgumentException("photoId is not included in holding list")
    }
  }
  */
}
