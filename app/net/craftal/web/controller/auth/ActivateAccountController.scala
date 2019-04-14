package net.craftal.web.controller.auth

import java.net.URLDecoder
import java.util.UUID

import com.mohiva.play.silhouette.api._
import javax.inject.Inject
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.usecase.auth.{Activate, RetryUserActivation}
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._

import scala.concurrent.ExecutionContext

class ActivateAccountController @Inject()(components: ControllerComponents,
                                          silhouette: Silhouette[DefaultEnv],
                                          activate: Activate,
                                          retryUserActivation: RetryUserActivation
                                         )(implicit ex: ExecutionContext) extends AbstractController(components) with I18nSupport {

  def send(email: String): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    val decodedEmail = URLDecoder.decode(email, "UTF-8")
    this.retryUserActivation.execute(decodedEmail).map {
      case true
      => Redirect(routes.SignInController.view()).flashing("info" -> Messages("activation.email.sent", decodedEmail))
      case false
      => Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link"))
    }
  }

  def activate(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    this.activate.execute(token).map {
      case true
      => Redirect(routes.SignInController.view()).flashing("success" -> Messages("account.activated"))
      case false
      => Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link"))
    }
  }
}
