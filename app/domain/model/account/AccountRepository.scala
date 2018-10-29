package domain.model.account

import scala.concurrent.Future

trait AccountRepository {

  def find(id: Int): Future[Account]

  def findByEmail(email: String): Future[Option[Account]]

  def findByEmailAndPassword(email: String, password: String): Future[Option[Account]]
}
