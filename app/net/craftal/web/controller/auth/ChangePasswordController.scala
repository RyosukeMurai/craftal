package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.auth.ChangePasswordForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.ChangePasswordViewPresenter
import net.craftal.web.usecase.auth.ChangePassword
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import net.craftal.web.controller.auth.routes

import scala.concurrent.{ExecutionContext, Future}

class ChangePasswordController @Inject()(
                                          components: ControllerComponents,
                                          silhouette: Silhouette[DefaultEnv],
                                          changePassword: ChangePassword,
                                          presenter: ChangePasswordViewPresenter
                                        )(
                                          implicit
                                          webJarsUtil: WebJarsUtil,
                                          assets: AssetsFinder,
                                          ex: ExecutionContext
                                        ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.SecuredAction(WithProvider[DefaultEnv#A](CredentialsProvider.ID)) {
    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
      Ok(presenter.present(ChangePasswordForm.form, request.identity))
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction(WithProvider[DefaultEnv#A](CredentialsProvider.ID)).async {
    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
      ChangePasswordForm.form.bindFromRequest.fold(
        form => Future.successful(BadRequest(presenter.present(form, request.identity))),
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
