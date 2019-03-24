package net.craftal.web.port.silhouette

import java.util.UUID

import com.mohiva.play.silhouette
import com.mohiva.play.silhouette.api.Authenticator.Implicits._
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.services.AuthenticatorResult
import com.mohiva.play.silhouette.api.util.{Clock, Credentials, PasswordHasherRegistry, PasswordInfo}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.exceptions.IdentityNotFoundException
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import javax.inject.Inject
import net.ceedubs.ficus.Ficus._
import net.craftal.identityaccess.api.AuthenticationService
import net.craftal.web.model.auth.AuthToken
import org.joda.time.DateTime
import play.api.Configuration
import play.api.mvc.{AnyContent, Request, Result}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future}

class SilhouetteServiceFacade @Inject()(authenticationService: AuthenticationService,
                                        userIdentityService: UserIdentityService,
                                        passwordHasherRegistry: PasswordHasherRegistry,
                                        credentialsProvider: CredentialsProvider,
                                        authInfoRepository: AuthInfoRepository,
                                        configuration: Configuration,
                                        clock: Clock)(implicit ex: ExecutionContext) {

  def retrieveAlreadyRegisteredAuthInfo(email: String): Future[Option[UserIdentity]] =
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

  def createAuthenticator(loginInfo: LoginInfo)(implicit request: Request[AnyContent]): Future[DefaultEnv#A] = silhouette.env.authenticatorService.create(loginInfo)

  def recreateWithNewExpiration(authenticator: DefaultEnv#A): CookieAuthenticator = {
    val c = configuration.underlying
    authenticator.copy(
      expirationDateTime = this.clock.now + c.as[FiniteDuration]("silhouette.authenticator.rememberMe.authenticatorExpiry"),
      idleTimeout = c.getAs[FiniteDuration]("silhouette.authenticator.rememberMe.authenticatorIdleTimeout"),
      cookieMaxAge = c.getAs[FiniteDuration]("silhouette.authenticator.rememberMe.cookieMaxAge")
    )
  }

  // TODO(RyosukeMurai): remove dependency to play framework
  def embedAuthenticationToResult(authenticator: DefaultEnv#A, result: Result)(implicit request: Request[AnyContent]): Future[AuthenticatorResult] = {
    //silhouette.env.eventBus.publish(LoginEvent(user, request)) //TODO(RyosukeMurai): consider when to use the event function of silhouette
    silhouette.env.authenticatorService.init(authenticator).flatMap { v =>
      silhouette.env.authenticatorService.embed(v, result)
    }
  }
}

