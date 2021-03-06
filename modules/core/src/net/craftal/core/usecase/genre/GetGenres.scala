package net.craftal.core.usecase.genre

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.genre.{Genre, GenreRepository}

import scala.concurrent.Future

class GetGenres @Inject()(repository: GenreRepository) extends Interactor {

  def execute(): Future[List[Genre]] = this.repository.findAll()

  def execute(genreIdList: List[Int]): Future[List[Genre]] = this.repository.findAll()
}
