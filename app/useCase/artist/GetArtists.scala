package useCase.artist

import domain.model.artist.{Artist, ArtistRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetArtists @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(keyword: Option[String]): Future[List[Artist]] =
    this.repository.findByKeyword(keyword)
}
