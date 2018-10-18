package domain.genre.interactor

import domain.genre.{Genre, GenreRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetGenres @Inject()(repository: GenreRepository) extends Interactor {

  def execute(): Future[List[Genre]] = this.repository.all()
}
