package net.craftal.web.usecase.auth

import com.mohiva.play.silhouette.api.util.PasswordHasherRegistry
import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService
import notification.NotificationService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class ChangePassword @Inject()(authenticationService: AuthenticationService,
                               notificationService: NotificationService,
                               passwordHasherRegistry: PasswordHasherRegistry)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String, currentPassword: String, newPassword: String)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    for {
      u <- this.authenticationService.getUser(email)
      result <- this.authenticationService.changePassword(u.get.id, currentPassword, newPassword)
    } yield result
  }
}
