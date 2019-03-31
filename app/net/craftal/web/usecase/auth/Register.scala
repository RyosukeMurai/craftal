package net.craftal.web.usecase.auth

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService
import notification.NotificationService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class Register @Inject()(authService: AuthenticationService,
                         notificationService: NotificationService)
                        (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String,
              name: String,
              password: String,
              verificationExpiration: FiniteDuration = 5 minutes)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.authService.getUser(email).flatMap {
      case Some(x) =>
        this.notificationService.notifyAlreadySignedUp(email, Option(x.name))
      case _ =>
        for {
          (userId, token) <- this.authService.register(email, "" /*hasher*/ , password, Option(name))
          user <- this.authService.getUser(userId)
          result <- this.notificationService.notifySignUp(user.email, Option(user.name), token)
        } yield result
    }
  }
}
