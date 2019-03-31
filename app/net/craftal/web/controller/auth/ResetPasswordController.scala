package net.craftal.web.controller.auth

import java.util.UUID

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.auth.ResetPasswordForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.ResetPasswordViewPresenter
import net.craftal.web.usecase.auth.ResetPassword
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import web.controller.auth.routes

import scala.concurrent.{ExecutionContext, Future}

class ResetPasswordController @Inject()(
                                         components: ControllerComponents,
                                         silhouette: Silhouette[DefaultEnv],
                                         resetPassword: ResetPassword,
                                         presenter: ResetPasswordViewPresenter
                                       )(
                                         implicit
                                         webJarsUtil: WebJarsUtil,
                                         assets: AssetsFinder,
                                         ex: ExecutionContext
                                       ) extends AbstractController(components) with I18nSupport {

  def view(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(presenter.present(ResetPasswordForm.form, token)))
  }

  def submit(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    ResetPasswordForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(presenter.present(form, token))),
      password => this.resetPassword.execute(token, password).map {
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
