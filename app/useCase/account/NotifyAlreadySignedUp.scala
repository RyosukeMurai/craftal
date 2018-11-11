package useCase.account

import javax.inject._
import useCase.Interactor
import useCase.shared.NotificationRequest
import web.service.MailService

import scala.concurrent.Future

@Singleton
class NotifyAlreadySignedUp @Inject()(mailService: MailService) extends Interactor {
  def execute(request: NotificationRequest): Future[Boolean] =
    Future.successful(
      this.mailService.send(
        request.title,
        request.senderIdentifier,
        request.destinationIdentifier,
        request.plainFormatContent,
        request.richFormatContent
      ))
}
