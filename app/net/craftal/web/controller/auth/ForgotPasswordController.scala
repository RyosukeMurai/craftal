package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.auth.ForgotPasswordForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.ForgotPasswordViewPresenter
import net.craftal.web.usecase.auth.ForgotPassword
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class ForgotPasswordController @Inject()(
                                          components: ControllerComponents,
                                          silhouette: Silhouette[DefaultEnv],
                                          forgotPassword: ForgotPassword,
                                          presenter: ForgotPasswordViewPresenter
                                        )(
                                          implicit
                                          webJarsUtil: WebJarsUtil,
                                          assets: AssetsFinder,
                                          ex: ExecutionContext
                                        ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(presenter.present(ForgotPasswordForm.form)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    ForgotPasswordForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      email => this.forgotPassword.execute(email).map(_ =>
        Redirect(routes.SignInController.view()).flashing("info" -> Messages("reset.email.sent"))
      )
    )
  }
}
