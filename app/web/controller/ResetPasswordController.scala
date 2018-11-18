package web.controller

import java.util.UUID

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import useCase.auth.{ResetPasswordByToken, ValidateAuthToken}
import web.DefaultEnv
import web.model.form.ResetPasswordForm

import scala.concurrent.{ExecutionContext, Future}

class ResetPasswordController @Inject()(
                                         components: ControllerComponents,
                                         silhouette: Silhouette[DefaultEnv],
                                         validateAuthToken: ValidateAuthToken,
                                         resetPasswordByToken: ResetPasswordByToken,
                                       )(
                                         implicit
                                         webJarsUtil: WebJarsUtil,
                                         assets: AssetsFinder,
                                         ex: ExecutionContext
                                       ) extends AbstractController(components) with I18nSupport {

  def view(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    this.validateAuthToken.execute(token).map {
      case true => Ok(web.view.auth.html.resetPassword(ResetPasswordForm.form, token))
      case false => Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.reset.link"))
    }
  }

  def submit(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    ResetPasswordForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(web.view.auth.html.resetPassword(form, token))),
      password => this.resetPasswordByToken.execute(token, password).map {
        case true =>
          Redirect(routes.SignInController.view())
            .flashing("success" -> Messages("password.reset"))
        case false =>
          Redirect(routes.SignInController.view())
            .flashing("error" -> Messages("invalid.reset.link"))
      }
    )
  }
}
