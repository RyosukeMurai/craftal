package net.craftal.web.usecase.auth

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService
import notification.NotificationService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class ForgotPassword @Inject()(authService: AuthenticationService,
                               notificationService: NotificationService)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.authService.getUser(email).flatMap {
      case Some(x) =>
        for {
          token <- this.authService.createIdentityToken(email)
          result <- this.notificationService.notifyForgotPassword(email, Option(x.name), token)
        } yield result
      case _ => Future.successful(false)
    }
  }
}
