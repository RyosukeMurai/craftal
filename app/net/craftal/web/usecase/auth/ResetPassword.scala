package net.craftal.web.usecase.auth

import java.util.UUID

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService
import net.craftal.web.port.silhouette.SilhouetteServiceFacade
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class ResetPassword @Inject()(authenticationService: AuthenticationService,
                              silhouetteServiceFacade: SilhouetteServiceFacade)
                             (implicit ex: ExecutionContext) extends Interactor {
  def execute(token: UUID, password: String)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    for {
      u <- this.authenticationService.authenticate(token)
      _ <- this.silhouetteServiceFacade.updatePasswordAuthInfo(u.email, password)
    } yield true
  }
}
