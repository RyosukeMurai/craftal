package web.service

import javax.inject.Inject

import com.mohiva.play.silhouette.api.Authenticator.Implicits._
import com.mohiva.play.silhouette.api.services.AuthenticatorResult
import com.mohiva.play.silhouette.api.util.Clock
import com.mohiva.play.silhouette.api.{LoginInfo, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import net.ceedubs.ficus.Ficus._
import play.api.Configuration
import play.api.mvc.{AnyContent, Request, Result}
import web.DefaultEnv

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future}

class AuthenticatorServiceFacade @Inject()(silhouette: Silhouette[DefaultEnv],
                                           credentialsProvider: CredentialsProvider,
                                           configuration: Configuration,
                                           clock: Clock)(implicit ex: ExecutionContext) {

  def create(loginInfo: LoginInfo)(implicit request: Request[AnyContent]): Future[DefaultEnv#A] = silhouette.env.authenticatorService.create(loginInfo)

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
