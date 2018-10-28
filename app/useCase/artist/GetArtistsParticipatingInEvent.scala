package useCase.artist

import domain.model.artist.{Artist, ArtistRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetArtistsParticipatingInEvent @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(eventId: Int): Future[List[Artist]] = this.repository.findArtistsByEventId(eventId)
}
