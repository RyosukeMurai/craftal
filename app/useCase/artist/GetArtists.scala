package useCase.artist

import domain.model.artist.{Artist, ArtistRepository}
import javax.inject._
import useCase.Interactor
import web.model.artist.ArtistSearchCondition

import scala.concurrent.Future

@Singleton
class GetArtists @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(findCondition: Option[ArtistSearchCondition]): Future[List[Artist]] =
    this.repository.findByKeyword(findCondition.map(condition => condition.keyword))
}
