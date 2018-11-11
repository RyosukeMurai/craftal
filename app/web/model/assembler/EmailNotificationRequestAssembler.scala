package web.model.assembler

import play.api.i18n._
import useCase.shared.NotificationRequest
import web.model.auth.UserIdentity

class EmailNotificationRequestAssembler() {
  def assembleForAlreadySignedUpEmail(toEmailAddress: String,
                                      userIdentity: UserIdentity,
                                      verificationUrl: String)(implicit messages: Messages): NotificationRequest =
    this.assemble(
      toEmailAddress = toEmailAddress,
      subject = messages("email.already.signed.up.subject"),
      bodyPlain = web.view.auth.emails.txt.alreadySignedUp(userIdentity, verificationUrl).body,
      bodyHtml = web.view.auth.emails.html.alreadySignedUp(userIdentity, verificationUrl).body
    )

  def assembleForSignUpEmail(userIdentity: UserIdentity,
                             verificationUrl: String)(implicit messages: Messages): NotificationRequest =
    userIdentity.email match {
      case Some(email) => this.assemble(
        toEmailAddress = email,
        subject = messages("email.sign.up.subject"),
        bodyPlain = web.view.auth.emails.txt.signUp(userIdentity, verificationUrl).body,
        bodyHtml = web.view.auth.emails.html.signUp(userIdentity, verificationUrl).body
      )
      case _ => throw new IllegalArgumentException("email is not included in specified identity")
    }

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
