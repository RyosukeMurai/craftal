package domain.model.account

import java.util.UUID

import org.joda.time.DateTime

import scala.concurrent.Future

trait AccountRepository {

  def find(id: Int): Future[Account]

  def findByEmail(email: String): Future[Option[Account]]

  def findByEmailAndPassword(email: String, password: String): Future[Option[Account]]

  def createAccount(userId: Int, password: String): Future[Int]

  def createAuthToken(token: UUID, userId: Int, expiry: DateTime): Future[String]
}
