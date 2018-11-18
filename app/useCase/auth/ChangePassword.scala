package useCase.auth

import auth.AuthService
import com.mohiva.play.silhouette.api.util.PasswordHasherRegistry
import javax.inject.{Inject, _}
import notification.NotificationService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import useCase.Interactor
import useCase.user.{GetUser, GetUserByEmail}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class ChangePassword @Inject()(authService: AuthService,
                               getUser: GetUser,
                               getUserByEmail: GetUserByEmail,
                               notificationService: NotificationService,
                               passwordHasherRegistry: PasswordHasherRegistry)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String, currentPassword: String, newPassword: String)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    for {
      u <- this.getUserByEmail.execute(email)
      result <- this.authService.changePassword(u.get.id, currentPassword, newPassword)
    } yield result
  }
}
