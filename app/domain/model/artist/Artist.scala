package domain.model.artist

import domain.model.genre.Genre
import domain.model.user.User

class Artist(id: Int,
             name: String,
             email: String,
             protected var genre: Genre,
             protected var photos: List[ArtistPhoto] = List()) extends User(id, name, email) {

  def getGenre: Genre = this.genre

  def setGenre(genre: Genre): Unit = this.genre = genre

  def getPhotos: List[ArtistPhoto] = photos.toList //immutable

  def setPhotos(photos: List[ArtistPhoto]): Unit = this.photos = photos

  def iconPhotoId: Int = {
    this.photos match {
      case x :: _ =>
      case _ => throw new IllegalAccessException(s"Artist ($this.title) doesn't have any photos.")
    }
    this.photos.sortWith(_.positionNo < _.positionNo).head.photoId
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Artist]

  override def equals(other: Any): Boolean = other match {
    case that: Artist =>
      (that canEqual this) &&
        photos == that.photos
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(photos)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
