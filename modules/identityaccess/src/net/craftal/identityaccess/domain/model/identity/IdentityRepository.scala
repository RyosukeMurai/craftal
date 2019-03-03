package net.craftal.identityaccess.domain.model.identity

import java.util.UUID

import org.joda.time.DateTime

import scala.concurrent.Future

trait IdentityRepository {

  def findPasswordIdentity(userId: Int): Future[PasswordIdentity]

  def findPasswordIdentityByEmail(email: String): Future[Option[PasswordIdentity]]

  def findIdentityTokenDetail(token: UUID): Future[Option[IdentityToken]]

  def createIdentityByPassword(userId: Int, hasher: String, hashedPassword: String): Future[Int]

  def createIdentityToken(userId: Int, token: UUID, expiry: DateTime): Future[String]

  def removeIdentityToken(token: UUID): Future[Boolean]

  def updateUserIdentity(userId: Int, isActivated: Boolean): Future[Boolean]

  def updatePassword(userId: Int, hasher: String, hashedPassword: String): Future[Boolean]
}
