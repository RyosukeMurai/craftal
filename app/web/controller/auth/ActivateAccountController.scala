package web.controller.auth

import java.net.URLDecoder
import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api._
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import useCase.auth.{ActivateUser, RetryUserActivation, ValidateAuthToken}
import web.DefaultEnv

import scala.concurrent.{ExecutionContext, Future}

class ActivateAccountController @Inject()(
                                           components: ControllerComponents,
                                           silhouette: Silhouette[DefaultEnv],
                                           validateAuthToken: ValidateAuthToken,
                                           activateUser: ActivateUser,
                                           retryUserActivation: RetryUserActivation
                                         )(
                                           implicit
                                           ex: ExecutionContext
                                         ) extends AbstractController(components) with I18nSupport {

  def send(email: String): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    val decodedEmail = URLDecoder.decode(email, "UTF-8")
    this.retryUserActivation.execute(decodedEmail).map {
      case true => Redirect(routes.SignInController.view()).flashing("info" -> Messages("activation.email.sent", decodedEmail))
      case false => Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link"))
    }
  }

  def activate(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    validateAuthToken.execute(token).flatMap {
      case true =>
        this.activateUser.execute(token).map {
          case true
          => Redirect(routes.SignInController.view()).flashing("success" -> Messages("account.activated"))
          case false
          => Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link"))
        }
      case false =>
        Future.successful(
          Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link"))
        )
    }
  }
}
