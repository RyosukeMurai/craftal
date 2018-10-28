package useCase.genre

import domain.model.genre.{Genre, GenreRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetGenres @Inject()(repository: GenreRepository) extends Interactor {

  def execute(): Future[List[Genre]] = this.repository.all()
}
