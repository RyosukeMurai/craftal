package domain.model.auth

import java.util.UUID

import org.joda.time.DateTime

import scala.concurrent.Future

trait AuthRepository {

  def findPasswordAuthInfo(id: Int): Future[PasswordAuthInfo]

  def findPasswordAuthInfoByEmail(email: String): Future[Option[PasswordAuthInfo]]

  def createAuthByPassword(userId: Int, hasher: String, hashedPassword: String, salt: String): Future[Int]

  def createAuthToken(token: UUID, userId: Int, expiry: DateTime): Future[String]
}
