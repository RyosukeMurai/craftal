package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.identityaccess.domain.model.role.RoleCode
import net.craftal.web.model.form.auth.SignUpForEventerForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.SignUpForEventerViewPresenter
import net.craftal.web.usecase.eventer.RegisterEventer
import net.craftal.web.usecase.genre.GetGenres
import net.craftal.web.usecase.prefecture.GetPrefectures
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class SignUpForEventerController @Inject()(components: ControllerComponents,
                                           silhouette: Silhouette[DefaultEnv],
                                           registerEventer: RegisterEventer,
                                           getPrefectures: GetPrefectures,
                                           getGenres: GetGenres,
                                           presenter: SignUpForEventerViewPresenter
                                          )(implicit
                                            webJarsUtil: WebJarsUtil,
                                            assets: AssetsFinder,
                                            ex: ExecutionContext
                                          ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(presenter.present(SignUpForEventerForm.form)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignUpForEventerForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      data =>
        this.registerEventer.execute(
          data.email, data.name, data.password
        ).map(_ =>
          Redirect(net.craftal.web.controller.auth.routes.SignInController.view())
            .flashing("info" -> Messages("sign.up.email.sent", data.email))
        )
    )
  }
}
