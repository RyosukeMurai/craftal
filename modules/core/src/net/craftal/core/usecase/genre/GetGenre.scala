package net.craftal.core.usecase.genre

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.genre.{Genre, GenreRepository}

import scala.concurrent.Future

class GetGenre @Inject()(repository: GenreRepository) extends Interactor {

  def execute(genreId: Int): Future[Genre] = this.repository.findGenre(genreId)
}
