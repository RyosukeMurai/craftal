package useCase.auth

import javax.inject.{Inject, _}

import auth.AuthService
import notification.NotificationService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import useCase.Interactor

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class RegisterUser @Inject()(authService: AuthService,
                             notificationService: NotificationService)
                            (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String,
              name: String,
              password: String,
              verificationExpiration: FiniteDuration = 5 minutes)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.authService.retrieveAlreadyRegisteredAuthInfo(email).flatMap {
      case Some(x) =>
        this.notificationService.notifyAlreadySignedUp(email, x.name)
      case _ =>
        for {
          account <- this.authService.registerAccountByPassword(email, name, password)
          result <- this.notificationService.notifySignUp(account.email, account.name, account.token)
        } yield result
    }
  }
}
