package web.controller.auth

import javax.inject.Inject

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import useCase.auth.RegisterUser
import web.DefaultEnv
import web.form.auth.SignUpForm

import scala.concurrent.{ExecutionContext, Future}

class SignUpController @Inject()(components: ControllerComponents,
                                 silhouette: Silhouette[DefaultEnv],
                                 registerUser: RegisterUser,
                                )(implicit
                                  webJarsUtil: WebJarsUtil,
                                  assets: AssetsFinder,
                                  ex: ExecutionContext
                                ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(web.view.auth.html.signUp(SignUpForm.form)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignUpForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(web.view.auth.html.signUp(form))),
      data => this.registerUser.execute(data.email, data.email, data.password).map(_ =>
        Redirect(web.controller.auth.routes.SignUpController.view())
          .flashing("info" -> Messages("sign.up.email.sent", data.email))
      )
    )
  }
}
