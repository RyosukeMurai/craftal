package useCase.artist

import domain.model.artist.{Artist, ArtistRepository}
import javax.inject._
import useCase.Interactor
import web.model.artist.ArtistSearchCondition

import scala.concurrent.Future

@Singleton
class GetArtistsByGenre @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(genreId: Int, findCondition: Option[ArtistSearchCondition]): Future[List[Artist]] =
    this.repository.findByGenreId(genreId, findCondition.map(condition => condition.keyword))
}