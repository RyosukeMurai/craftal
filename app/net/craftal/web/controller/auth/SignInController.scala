package net.craftal.web.controller.auth

import javax.inject.Inject
import auth.exception.NotActivatedException
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.impl.providers._
import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import usecase.auth.Authenticate

import scala.concurrent.{ExecutionContext, Future}

class SignInController @Inject()(
                                  components: ControllerComponents,
                                  silhouette: Silhouette[DefaultEnv],
                                  signInByPassword: Authenticate,
                                  socialProviderRegistry: SocialProviderRegistry,
                                )(
                                  implicit
                                  webJarsUtil: WebJarsUtil,
                                  assets: AssetsFinder,
                                  ex: ExecutionContext
                                ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(web.view.auth.html.signIn(SignInForm.form, socialProviderRegistry)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignInForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(web.view.auth.html.signIn(form, socialProviderRegistry))),
      data => {
        this.signInByPassword.execute(data.email, data.password, data.rememberMe, Redirect(routes.ApplicationController.index()))
          .recover {
            case _: NotActivatedException => //TODO(RyosukeMurai): remove dependency to auth module
              Ok(web.view.auth.html.activateAccount(data.email))
            case e: ProviderException =>
              Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.credentials"))
          }
      })
  }
}
