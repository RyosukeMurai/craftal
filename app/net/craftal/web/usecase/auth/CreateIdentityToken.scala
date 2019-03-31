package net.craftal.web.usecase.auth

import java.util.UUID

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class CreateIdentityToken @Inject()(authenticationService: AuthenticationService)
                                   (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String): Future[UUID] =
    this.authenticationService.createIdentityToken(email)
}
