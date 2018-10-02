package domain.artist

import scala.concurrent.Future

trait ArtistRepository {

  def find(id: Int): Future[Artist]

  def findArtistsByEventId(eventId: Int): Future[List[Artist]]
}
