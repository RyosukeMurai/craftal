package notification

import play.api.i18n._

class EmailNotificationRequestAssembler {
  def assembleForAlreadySignedUpEmail(toEmailAddress: String,
                                      recipientName: Option[String],
                                      signInUrl: String)(implicit messages: Messages): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = messages("email.already.signed.up.subject"),
      bodyPlain = notification.template.email.txt.alreadySignedUp(recipientName, signInUrl).body,
      bodyHtml = notification.template.email.html.alreadySignedUp(recipientName, signInUrl).body
    )

  def assembleForSignUpEmail(toEmailAddress: String,
                             recipientName: Option[String],
                             verificationUrl: String)(implicit messages: Messages): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = messages("email.sign.up.subject"),
      bodyPlain = notification.template.email.txt.signUp(recipientName, verificationUrl).body,
      bodyHtml = notification.template.email.html.signUp(recipientName, verificationUrl).body
    )

  def assembleForResetPasswordEmail(toEmailAddress: String,
                                    recipientName: Option[String],
                                    verificationUrl: String)(implicit messages: Messages): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = Messages("email.reset.password.subject"),
      bodyPlain = notification.template.email.txt.resetPassword(recipientName, verificationUrl).body,
      bodyHtml = notification.template.email.html.resetPassword(recipientName, verificationUrl).body
    )

  private def assemble(toEmailAddress: String,
                       subject: String,
                       bodyPlain: String,
                       bodyHtml: String)(implicit messages: Messages): NotificationRequest = {
    NotificationRequest(
      destinationIdentifier = Seq(toEmailAddress),
      senderIdentifier = Messages("email.from"),
      title = subject,
      plainFormatContent = Some(bodyPlain),
      richFormatContent = Some(bodyHtml)
    )
  }
}
