package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.auth.SignUpForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.SignUpViewPresenter
import net.craftal.web.usecase.member.RegisterMember
import net.craftal.web.usecase.prefecture.GetPrefectures
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._

import scala.concurrent.ExecutionContext

class SignUpController @Inject()(components: ControllerComponents,
                                 silhouette: Silhouette[DefaultEnv],
                                 registerUser: RegisterMember,
                                 getPrefectures: GetPrefectures,
                                 presenter: SignUpViewPresenter
                                )(implicit
                                  webJarsUtil: WebJarsUtil,
                                  assets: AssetsFinder,
                                  ex: ExecutionContext
                                ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    this.getPrefectures.execute.map(response => Ok(presenter.present(SignUpForm.form, response)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignUpForm.form.bindFromRequest.fold(
      form => this.getPrefectures.execute.map(response => BadRequest(presenter.present(form, response))),
      data => this.registerUser.execute(data.email, data.name, data.password).map(_ =>
        Redirect(net.craftal.web.controller.auth.routes.SignInController.view())
          .flashing("info" -> Messages("sign.up.email.sent", data.email))
      )
    )
  }
}
