package notification

import java.util.UUID

import javax.inject.Inject
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

//"auth" is an abbreviation containing authorization and authentication
class NotificationService @Inject()(mailService: MailService,
                                    emailAssembler: EmailNotificationRequestAssembler)
                                   (implicit ex: ExecutionContext) {

  def notifyAlreadySignedUp(email: String, name: Option[String])
                           (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.sendNotificationByEmail(
      this.emailAssembler.assembleForAlreadySignedUpEmail(
        email,
        name,
        web.controller.routes.SignInController.view().absoluteURL()
      ))
  }

  def notifySignUp(email: String, name: Option[String], token: UUID)
                  (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.sendNotificationByEmail(
      this.emailAssembler.assembleForSignUpEmail(
        email,
        name,
        web.controller.routes.ActivateAccountController.activate(token).absoluteURL()
      ))
    //TODO(RyosukeMurai): Don't use it now
    //silhouette.env.eventBus.publish(SignUpEvent(r.user, request))
  }

  def notifyForgotPassword(email: String, name: Option[String], token: UUID)
                          (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.sendNotificationByEmail(
      this.emailAssembler.assembleForResetPasswordEmail(
        email,
        name,
        web.controller.routes.ResetPasswordController.view(token).absoluteURL()
      ))
  }

  private def sendNotificationByEmail(request: NotificationRequest): Future[Boolean] =
    Future.successful(
      this.mailService.send(
        request.title,
        request.senderIdentifier,
        request.destinationIdentifier,
        request.plainFormatContent,
        request.richFormatContent
      ))
}
