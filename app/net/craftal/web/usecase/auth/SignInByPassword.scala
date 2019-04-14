package net.craftal.web.usecase.auth

import com.mohiva.play.silhouette.api.Silhouette
import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.web.port.silhouette.{DefaultEnv, SilhouetteAuthenticatorFactory, SilhouetteServiceFacade}
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class SignInByPassword @Inject()(silhouetteServiceFacade: SilhouetteServiceFacade,
                                 authenticatorFactory: SilhouetteAuthenticatorFactory)
                                (implicit ex: ExecutionContext) extends Interactor {

  def execute(email: String,
              password: String,
              rememberMe: Boolean)
             (implicit request: Request[AnyContent],
              messages: Messages,
              silhouette: Silhouette[DefaultEnv]): Future[DefaultEnv#A] = {
    for {
      l <- this.silhouetteServiceFacade.authenticate(email, password) //authenticate by silhouette
      authenticator <- this.authenticatorFactory.generate(silhouette, l).map {
        case a if rememberMe => this.authenticatorFactory.regenerateWithNewExpiration(silhouette, a)
        case a => a
      }
    } yield authenticator
  }
}
