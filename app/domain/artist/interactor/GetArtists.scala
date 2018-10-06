package domain.artist.interactor

import domain.artist.{Artist, ArtistRepository}
import domain.shared.Interactor
import javax.inject._
import presentation.model.artist.ArtistSearchCondition

import scala.concurrent.Future

@Singleton
class GetArtists @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(findCondition: Option[ArtistSearchCondition]): Future[List[Artist]] =
    this.repository.findByKeyword(findCondition.map(condition => condition.keyword))
}
