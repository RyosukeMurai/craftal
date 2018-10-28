package useCase.artist

import domain.model.artist.{Artist, ArtistRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetArtist @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(id: Int): Future[Artist] = this.repository.find(id)
}
