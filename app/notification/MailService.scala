package notification

import javax.inject.Inject
import play.api.libs.mailer.{Email, MailerClient}

class MailService @Inject()(mailerClient: MailerClient) {
  def send(subject: String, from: String, to: Seq[String], bodyText: Option[String], bodyHtml: Option[String]): Boolean = {
    this.mailerClient.send(Email(
      subject = subject,
      from = from,
      to = to,
      bodyText = bodyText,
      bodyHtml = bodyHtml
    ))
    true
  }
}
