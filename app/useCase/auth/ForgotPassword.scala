package useCase.auth

import auth.AuthService
import javax.inject.{Inject, _}
import notification.{EmailNotificationRequestAssembler, NotificationService}
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class ForgotPassword @Inject()(authService: AuthService,
                               notificationService: NotificationService,
                               emailAssembler: EmailNotificationRequestAssembler)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.authService.retrieveAlreadyRegisteredAuthInfo(email).flatMap {
      case Some(x) =>
        for {
          token <- this.authService.createNewAuthToken(email)
          result <- this.notificationService.notifyForgotPassword(email, x.name, token)
        } yield result
      case _ => Future.successful(false)
    }
  }
}
