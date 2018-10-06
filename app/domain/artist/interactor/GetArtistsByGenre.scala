package domain.artist.interactor

import domain.artist.{Artist, ArtistRepository}
import domain.shared.Interactor
import javax.inject._
import presentation.model.artist.ArtistSearchCondition

import scala.concurrent.Future

@Singleton
class GetArtistsByGenre @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(genreId: Int, findCondition: Option[ArtistSearchCondition]): Future[List[Artist]] =
    this.repository.findByGenreId(genreId, findCondition.map(condition => condition.keyword))
}
