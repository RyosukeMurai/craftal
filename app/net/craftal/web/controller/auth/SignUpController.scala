package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.auth.SignUpForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.SignUpViewPresenter
import net.craftal.web.usecase.auth.Register
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class SignUpController @Inject()(components: ControllerComponents,
                                 silhouette: Silhouette[DefaultEnv],
                                 registerUser: Register,
                                 presenter: SignUpViewPresenter
                                )(implicit
                                  webJarsUtil: WebJarsUtil,
                                  assets: AssetsFinder,
                                  ex: ExecutionContext
                                ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(presenter.present(SignUpForm.form)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignUpForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      data => this.registerUser.execute(data.email, data.name, data.password).map(_ =>
        Redirect(net.craftal.web.controller.auth.routes.SignUpController.view())
          .flashing("info" -> Messages("sign.up.email.sent", data.email))
      )
    )
  }
}
