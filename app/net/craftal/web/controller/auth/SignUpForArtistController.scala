package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.identityaccess.domain.model.role.RoleCode
import net.craftal.web.model.form.auth.SignUpForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.auth.SignUpForArtistViewPresenter
import net.craftal.web.usecase.auth.Register
import net.craftal.web.usecase.genre.GetGenres
import net.craftal.web.usecase.prefecture.GetPrefectures
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._

import scala.concurrent.ExecutionContext

class SignUpForArtistController @Inject()(components: ControllerComponents,
                                          silhouette: Silhouette[DefaultEnv],
                                          registerUser: Register,
                                          getPrefectures: GetPrefectures,
                                          getGenres: GetGenres,
                                          presenter: SignUpForArtistViewPresenter
                                         )(implicit
                                           webJarsUtil: WebJarsUtil,
                                           assets: AssetsFinder,
                                           ex: ExecutionContext
                                         ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    this.getPrefectures.execute.zip(this.getGenres.execute)
      .map(response => Ok(presenter.present(SignUpForm.form, response._1, response._2)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignUpForm.form.bindFromRequest.fold(
      form => this.getPrefectures.execute.zip(this.getGenres.execute)
        .map(response => BadRequest(presenter.present(form, response._1, response._2))),
      data => this.registerUser.execute(data.email, data.name, data.password, RoleCode.artist.toString).map(_ =>
        Redirect(net.craftal.web.controller.auth.routes.SignInController.view())
          .flashing("info" -> Messages("sign.up.email.sent", data.email))
      )
    )
  }
}
