package useCase.auth

import java.util.UUID

import auth.AuthService
import javax.inject.{Inject, _}
import notification.{EmailNotificationRequestAssembler, NotificationService}
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class ValidateAuthToken @Inject()(authService: AuthService,
                                  notificationService: NotificationService,
                                  emailAssembler: EmailNotificationRequestAssembler)
                                 (implicit ex: ExecutionContext) extends Interactor {
  def execute(token: UUID)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.authService.validateAuthToken(token).map(_.isDefined)
  }
}
