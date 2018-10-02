package domain.artist.interactor

import domain.artist.{Artist, ArtistRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetArtistsParticipatingInEvent @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(eventId: Int): Future[List[Artist]] = this.repository.findArtistsByEventId(eventId)
}
