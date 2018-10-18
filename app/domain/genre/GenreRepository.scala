package domain.genre

import scala.concurrent.Future

trait GenreRepository {

  def find(id: Int): Future[Genre]

  def all(): Future[List[Genre]]
}
