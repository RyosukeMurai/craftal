package domain.artist.interactor

import domain.artist.{Artist, ArtistRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetArtist @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(id: Int): Future[Artist] = this.repository.find(id)
}
