package useCase.auth

import javax.inject.{Inject, _}

import auth.AuthService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request, Result}
import useCase.Interactor
import web.service.AuthenticatorServiceFacade

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class SignInByPassword @Inject()(authService: AuthService,
                                 authenticatorServiceFacade: AuthenticatorServiceFacade)
                                (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String,
              password: String,
              rememberMe: Boolean = false,
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
