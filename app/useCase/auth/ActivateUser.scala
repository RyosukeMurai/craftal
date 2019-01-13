package useCase.auth

import java.util.UUID
import javax.inject.{Inject, _}

import auth.AuthService
import notification.{EmailNotificationRequestAssembler, NotificationService}
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class ActivateUser @Inject()(authService: AuthService)(implicit ex: ExecutionContext) extends Interactor {

  def execute(token: UUID)
             (implicit request: Request[AnyContent]): Future[Boolean] = {
    this.authService.activateUser(token)
  }
}
