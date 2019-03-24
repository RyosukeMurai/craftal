package net.craftal.web.controller.auth

import javax.inject.Inject
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import usecase.auth.ChangePassword

import scala.concurrent.{ExecutionContext, Future}

class ChangePasswordController @Inject()(
                                          components: ControllerComponents,
                                          silhouette: Silhouette[DefaultEnv],
                                          changePassword: ChangePassword
                                        )(
                                          implicit
                                          webJarsUtil: WebJarsUtil,
                                          assets: AssetsFinder,
                                          ex: ExecutionContext
                                        ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.SecuredAction(WithProvider[DefaultEnv#A](CredentialsProvider.ID)) {
    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
      Ok(web.view.auth.html.changePassword(ChangePasswordForm.form, request.identity))
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction(WithProvider[DefaultEnv#A](CredentialsProvider.ID)).async {
    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
      ChangePasswordForm.form.bindFromRequest.fold(
        form => Future.successful(BadRequest(web.view.auth.html.changePassword(form, request.identity))),
        password => {
          val (currentPassword, newPassword) = password
          this.changePassword.execute(request.identity.email.getOrElse(""), currentPassword, newPassword).map { _ =>
            Redirect(routes.ChangePasswordController.view()).flashing("success" -> Messages("password.changed"))
          }.recover {
            case _: ProviderException =>
              Redirect(routes.ChangePasswordController.view()).flashing("error" -> Messages("current.password.invalid"))
          }
        }
      )
  }
}
