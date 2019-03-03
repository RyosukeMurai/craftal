package net.craftal.core.domain.model.genre

import scala.concurrent.Future

trait GenreRepository {

  def findGenre(genreId: Int): Future[Genre]

  def findAll(): Future[List[Genre]]
}
