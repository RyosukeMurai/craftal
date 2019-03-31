package net.craftal.web.port.silhouette

import com.mohiva.play.silhouette.api.Authenticator.Implicits._
import com.mohiva.play.silhouette.api.services.AuthenticatorResult
import com.mohiva.play.silhouette.api.util.Clock
import com.mohiva.play.silhouette.api.{LoginInfo, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import javax.inject.Inject
import net.ceedubs.ficus.Ficus._
import play.api.Configuration
import play.api.mvc.{AnyContent, Request, Result}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future}

class SilhouetteAuthenticatorFactory @Inject()(configuration: Configuration,
                                               clock: Clock)(implicit ex: ExecutionContext) {

  def generate(silhouette: Silhouette[DefaultEnv],
               loginInfo: LoginInfo)
              (implicit request: Request[AnyContent]): Future[DefaultEnv#A] = {
    silhouette.env.authenticatorService.create(loginInfo)
  }

  def regenerateWithNewExpiration(silhouette: Silhouette[DefaultEnv],
                                  authenticator: DefaultEnv#A): CookieAuthenticator = {
    val c = configuration.underlying
    authenticator.copy(
      expirationDateTime = this.clock.now + c.as[FiniteDuration]("silhouette.authenticator.rememberMe.authenticatorExpiry"),
      idleTimeout = c.getAs[FiniteDuration]("silhouette.authenticator.rememberMe.authenticatorIdleTimeout"),
      cookieMaxAge = c.getAs[FiniteDuration]("silhouette.authenticator.rememberMe.cookieMaxAge")
    )
  }
}

