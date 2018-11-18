package domain.model.auth

import java.util.UUID

import org.joda.time.DateTime

import scala.concurrent.Future

trait AuthRepository {

  def findPasswordAuthInfo(id: Int): Future[PasswordAuthInfo]

  def findPasswordAuthInfoByEmail(email: String): Future[Option[PasswordAuthInfo]]

  def createAuthByPassword(userId: Int, hasher: String, hashedPassword: String): Future[Int]

  def createAuthToken(userId: Int, token: UUID, expiry: DateTime): Future[String]

  def findAuthToken(token: UUID): Future[Option[AuthToken]]

  def removeAuthToken(token: UUID): Future[Boolean]
}
