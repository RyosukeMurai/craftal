package domain.artist

import scala.concurrent.Future

trait ArtistRepository {

  def find(id: Int): Future[Artist]

  def findArtistsByEventId(eventId: Int): Future[List[Artist]]

  def findByKeyword(keyword: Option[String]): Future[List[Artist]]

  def all(): Future[List[Artist]]
}
