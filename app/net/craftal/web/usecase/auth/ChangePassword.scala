package net.craftal.web.usecase.auth

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.web.port.silhouette.SilhouetteServiceFacade
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class ChangePassword @Inject()(silhouetteServiceFacade: SilhouetteServiceFacade)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String, currentPassword: String, newPassword: String)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    for {
      _ <- this.silhouetteServiceFacade.updatePasswordAuthInfo(email, currentPassword, newPassword)
    } yield true
  }
}
