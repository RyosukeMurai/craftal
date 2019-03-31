package net.craftal.web.usecase.auth

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class CreateAuthInfoByPassword @Inject()(authenticationService: AuthenticationService)
                                        (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, hasher: String, hashedPassword: String): Future[Int] =
    this.authenticationService.register(userId, hasher, hashedPassword, None)
}
