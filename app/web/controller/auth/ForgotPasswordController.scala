package web.controller.auth

import javax.inject.Inject

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import useCase.auth.ForgotPassword
import web.DefaultEnv
import web.form.auth.ForgotPasswordForm

import scala.concurrent.{ExecutionContext, Future}

class ForgotPasswordController @Inject()(
                                          components: ControllerComponents,
                                          silhouette: Silhouette[DefaultEnv],
                                          forgotPassword: ForgotPassword
                                        )(
                                          implicit
                                          webJarsUtil: WebJarsUtil,
                                          assets: AssetsFinder,
                                          ex: ExecutionContext
                                        ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(web.view.auth.html.forgotPassword(ForgotPasswordForm.form)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    ForgotPasswordForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(web.view.auth.html.forgotPassword(form))),
      email => this.forgotPassword.execute(email).map(_ =>
        Redirect(routes.SignInController.view()).flashing("info" -> Messages("reset.email.sent"))
      )
    )
  }
}
