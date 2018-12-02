package useCase.artist

import domain.model.artist.{Artist, ArtistRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetArtistsByGenre @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(genreId: Int, keyword: Option[String]): Future[List[Artist]] =
    this.repository.findByGenreId(genreId, keyword)
}
