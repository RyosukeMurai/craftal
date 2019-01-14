package notification

import java.util.UUID

import play.api.i18n._
import play.api.mvc.{AnyContent, Request}

class EmailNotificationRequestAssembler {
  def assembleForAlreadySignedUpEmail(toEmailAddress: String,
                                      recipientName: Option[String]
                                     )(implicit messages: Messages, request: Request[AnyContent]): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = messages("email.already.signed.up.subject"),
      bodyPlain = notification.template.email.txt.alreadySignedUp(recipientName).body,
      bodyHtml = notification.template.email.html.alreadySignedUp(recipientName).body
    )

  def assembleForSignUpEmail(toEmailAddress: String,
                             recipientName: Option[String],
                             token: UUID)(implicit messages: Messages, request: Request[AnyContent]): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = messages("email.sign.up.subject"),
      bodyPlain = notification.template.email.txt.signUp(recipientName, token).body,
      bodyHtml = notification.template.email.html.signUp(recipientName, token).body
    )

  def assembleForUserActivationEmail(toEmailAddress: String,
                                     recipientName: Option[String],
                                     token: UUID)(implicit messages: Messages, request: Request[AnyContent]): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = messages("email.activate.account.subject"),
      bodyPlain = notification.template.email.txt.activateAccount(recipientName, token).body,
      bodyHtml = notification.template.email.html.activateAccount(recipientName, token).body
    )


  def assembleForResetPasswordEmail(toEmailAddress: String,
                                    recipientName: Option[String],
                                    token: UUID)(implicit messages: Messages, request: Request[AnyContent]): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = Messages("email.reset.password.subject"),
      bodyPlain = notification.template.email.txt.resetPassword(recipientName, token).body,
      bodyHtml = notification.template.email.html.resetPassword(recipientName, token).body
    )

  private def assemble(toEmailAddress: String,
                       subject: String,
                       bodyPlain: String,
                       bodyHtml: String)(implicit messages: Messages, request: Request[AnyContent]): NotificationRequest = {
    NotificationRequest(
      destinationIdentifier = Seq(toEmailAddress),
      senderIdentifier = Messages("email.from"),
      title = subject,
      plainFormatContent = Some(bodyPlain),
      richFormatContent = Some(bodyHtml)
    )
  }
}
