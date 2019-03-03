package net.craftal.identityaccess.domain.model.user

import java.util.UUID

import scala.concurrent.Future

trait UserRepository {

  def findUser(userId: Int): Future[User]

  def findUserByEmail(email: String): Future[Option[User]]

  def findUserByToken(token: UUID): Future[Option[User]]

  def createUser(name: String, email: String): Future[Int]
}
