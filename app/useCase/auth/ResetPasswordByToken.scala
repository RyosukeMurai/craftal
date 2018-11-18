package useCase.auth

import java.util.UUID

import auth.AuthService
import com.mohiva.play.silhouette.api.util.PasswordHasherRegistry
import javax.inject.{Inject, _}
import notification.NotificationService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import useCase.Interactor
import useCase.user.GetUser

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class ResetPasswordByToken @Inject()(authService: AuthService,
                                     getUser: GetUser,
                                     notificationService: NotificationService,
                                     passwordHasherRegistry: PasswordHasherRegistry)
                                    (implicit ex: ExecutionContext) extends Interactor {
  def execute(token: UUID, password: String /*, providerID == CredentialsProvider.ID */)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    for {
      t <- this.authService.validateAuthToken(token)
      result <- this.authService.resetPassword(t.get.userId, password) //TODO(RyosukeMurai): figure out fail pattern
    } yield result
  }
}
