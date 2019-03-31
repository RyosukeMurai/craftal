package net.craftal.web.usecase.auth

import java.util.UUID

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class Activate @Inject()(authenticationService: AuthenticationService)
                        (implicit ex: ExecutionContext) extends Interactor {

  def execute(token: UUID)
             (implicit request: Request[AnyContent]): Future[Boolean] =
    this.authenticationService.activate(token)
}

