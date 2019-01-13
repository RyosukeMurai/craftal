package domain.model.user

import java.util.UUID

import scala.concurrent.Future

trait UserRepository {

  def find(id: Int): Future[User] //TODO(RyosukeMurai): should i use UserId class?

  def findByEmail(email: String): Future[Option[User]]

  def findByToken(token: UUID): Future[Option[User]]

  def createUser(name: String, email: String): Future[Int]
}
