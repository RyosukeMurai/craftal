package net.craftal.identityaccess.api

import java.util.UUID

import javax.inject.Inject
import net.craftal.identityaccess.domain.model.identity.PasswordIdentity
import net.craftal.identityaccess.domain.model.role.Role
import net.craftal.identityaccess.domain.model.role.RoleCode.RoleCode
import net.craftal.identityaccess.domain.model.user.User
import net.craftal.identityaccess.usecase._

import scala.concurrent.{ExecutionContext, Future}

class AuthenticationService @Inject()(activate: Activate,
                                      authenticate: Authenticate,
                                      changePassword: ChangePassword,
                                      createIdentityToken: CreateIdentityToken,
                                      getUser: GetUser,
                                      getPasswordIdentity: GetPasswordIdentity,
                                      getUserRole: GetUserRole,
                                      isActivated: IsActivated,
                                      register: Register,
                                      assumeRole: AssumeRole)
                                     (implicit ex: ExecutionContext) {

  def activate(token: UUID): Future[Boolean] = this.activate.execute(token)

  def authenticate(email: String): Future[User] = this.authenticate.execute(email)

  def authenticate(token: UUID): Future[User] = this.authenticate.execute(token)

  def changePassword(userId: Int, hasher: String, hashedPassword: String): Future[Boolean] =
    this.changePassword.execute(userId, hasher, hashedPassword)

  def createIdentityToken(email: String): Future[UUID] = this.createIdentityToken.execute(email)

  def getUser(userId: Int): Future[User] = this.getUser.execute(userId)

  def getUser(email: String): Future[Option[User]] = this.getUser.execute(email)

  def getUserRole(userId: Int): Future[Role] = this.getUserRole.execute(userId)

  def getPasswordIdentity(userId: Int): Future[PasswordIdentity] = this.getPasswordIdentity.execute(userId)

  def getPasswordIdentity(email: String): Future[PasswordIdentity] = this.getPasswordIdentity.execute(email)

  def isActivated(userId: Int): Future[Boolean] = this.isActivated(userId)

  def register(email: String, hasher: String, password: String, name: Option[String] = None): Future[Int] =
    this.register.execute(email, hasher, password, name)

  def assumeRole(userId: Int, role: Role): Future[Boolean] = this.assumeRole.execute(userId, role)

  def assumeRole(userId: Int, roleCode: RoleCode): Future[Boolean] = this.assumeRole.execute(userId, roleCode)
}