package net.craftal.web.usecase.genre

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.genre.{Genre, GenreRepository}

import scala.concurrent.Future

@Singleton
class GetGenres @Inject()(repository: GenreRepository) extends Interactor {

  def execute(): Future[List[Genre]] = this.repository.findAll()
}
