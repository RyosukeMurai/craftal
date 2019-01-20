package auth

import java.util.UUID
import javax.inject.Inject

import auth.exception.NotActivatedException
import auth.model.UserAuthInfo
import auth.model.response.AccountRegistrationResponse
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.util.{Credentials, PasswordHasherRegistry, PasswordInfo}
import com.mohiva.play.silhouette.impl.exceptions.IdentityNotFoundException
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import domain.model.auth.AuthToken
import org.joda.time.DateTime
import useCase.auth.{CreateAuthToken, GetAuthToken}
import useCase.user._

import scala.concurrent.{ExecutionContext, Future}

//"auth" is an abbreviation containing authorization and authentication
class AuthService @Inject()(createUser: CreateUser,
                            createAuthToken: CreateAuthToken,
                            getAuthToken: GetAuthToken,
                            getUser: GetUser,
                            getUserByEmail: GetUserByEmail,
                            getUserByToken: GetUserByToken,
                            updateUserAuth: UpdateUserAuth,
                            userIdentityService: UserIdentityService,
                            passwordHasherRegistry: PasswordHasherRegistry,
                            credentialsProvider: CredentialsProvider,
                            authInfoRepository: AuthInfoRepository)(implicit ex: ExecutionContext) {

  def retrieveAlreadyRegisteredAuthInfo(email: String): Future[Option[UserAuthInfo]] =
    this.userIdentityService.retrieve(LoginInfo(CredentialsProvider.ID, email))

  def registerAccountByPassword(email: String, name: String, password: String): Future[AccountRegistrationResponse] = {
    val loginInfo = LoginInfo(CredentialsProvider.ID, email)
    val passwordInfo = this.passwordHasherRegistry.current.hash(password)
    for {
      userId <- this.createUser.execute(name, email)
      user <- this.getUser.execute(userId)
      _ <- this.authInfoRepository.add(loginInfo, passwordInfo)
      token <- this.createAuthToken.execute(userId)
    } yield AccountRegistrationResponse(
      user.email, Option(user.name), activated = false, token
    )
  }

  def createNewAuthToken(email: String): Future[UUID] = {
    for {
      user <- this.getUserByEmail.execute(email)
      token <- this.createAuthToken.execute(user.get.id) //TODO(RyosukeMurai): should i handle exception when can't get user by email?
    } yield token
  }

  def validateAuthToken(token: UUID): Future[Option[AuthToken]] = {
    this.getAuthToken.execute(token).map {
      _.collect {
        case t if t.expiry.isAfter(new DateTime()) => t
      }
    }
  }

  def resetPassword(userId: Int, password: String): Future[Boolean] = {
    for {
      u <- this.getUser.execute(userId)
      _ <- this.authInfoRepository.update[PasswordInfo](
        LoginInfo(CredentialsProvider.ID, u.email),
        passwordHasherRegistry.current.hash(password)
      )
    } yield true
  }

  def changePassword(userId: Int, currentPassword: String, newPassword: String): Future[Boolean] = {
    for {
      u <- this.getUser.execute(userId)
      c <- this.credentialsProvider.authenticate(Credentials(u.email, currentPassword))
      _ <- this.authInfoRepository.update[PasswordInfo](
        LoginInfo(CredentialsProvider.ID, c.providerKey),
        this.passwordHasherRegistry.current.hash(newPassword)
      )
    } yield true
  }

  def activateUser(token: UUID): Future[Boolean] = {
    for {
      user <- this.getUserByToken.execute(token)
      result <- this.updateUserAuth.execute(user.get.id, isActivated = true) //TODO(RyosukeMurai): should i handle exception when can't get user by email?
    } yield result
  }

  def authenticate(email: String, password: String): Future[LoginInfo] = {
    for {
      l <- this.credentialsProvider.authenticate(Credentials(email, password))
      _ <- this.userIdentityService.retrieve(l).map {
        case Some(x) if !x.activated => throw new NotActivatedException("specified user has not been activated")
        case Some(x) => x
        case None => throw new IdentityNotFoundException("there is no user with the specified email address") //TODO (RyosukeMurai): remove dependency to silhouette exception class
      }
    } yield l
  }
}
