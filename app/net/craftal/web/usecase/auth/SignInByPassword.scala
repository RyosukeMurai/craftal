package net.craftal.web.usecase.auth

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService
import net.craftal.identityaccess.domain.model.user.User
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request, Result}
import web.service.SilhouetteServiceFacade

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class SignInByPassword @Inject()(authService: AuthenticationService,
                                 authenticatorServiceFacade: SilhouetteServiceFacade)
                                (implicit ex: ExecutionContext) extends Interactor {

  def execute(email: String,
              password: String,
              rememberMe: Boolean,
              result: Result)
             (implicit request: Request[AnyContent], messages: Messages): Future[Result] = {
    for {
      loginInfo <- this.authService.authenticate(email, password)
      authenticator <- this.authenticatorServiceFacade.create(loginInfo).map {
        case a if rememberMe => this.authenticatorServiceFacade.recreateWithNewExpiration(a)
        case a => a
      }
      embedded <- this.authenticatorServiceFacade.embedAuthenticationToResult(authenticator, result)
    } yield embedded
  }
}
