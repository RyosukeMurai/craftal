package domain.photo

import scala.concurrent.Future

trait PhotoRepository {

  def find(id: Int): Future[Photo]

  def findByIdList(idList: List[Int]): Future[List[Photo]]
}
