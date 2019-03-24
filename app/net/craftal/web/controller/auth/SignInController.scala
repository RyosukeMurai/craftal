package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.auth.SignInForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.SignInViewPresenter
import net.craftal.web.usecase.auth.SignInByPassword
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class SignInController @Inject()(
                                  components: ControllerComponents,
                                  silhouette: Silhouette[DefaultEnv],
                                  signInByPassword: SignInByPassword,
                                  presenter: SignInViewPresenter
                                )(
                                  implicit
                                  webJarsUtil: WebJarsUtil,
                                  assets: AssetsFinder,
                                  ex: ExecutionContext
                                ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok())
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignInForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      data => {
        this.signInByPassword.execute(data.email, data.password, data.rememberMe)
          .recover {
            case _: VerifyError =>
              Ok(presenter.presentActivate(data.email))
            case e: Exception =>
              Redirect(web.controller.auth.routes.SignInController.view()).flashing("error" -> Messages("invalid.credentials"))
          }
          .map { _ =>
            Redirect(routes.ApplicationController.index())
          }
      })
  }
}
