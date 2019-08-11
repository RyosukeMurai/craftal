package net.craftal.web.controller.mypage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.model.form.mypage.EditArtistProfileForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.mypage.ArtistProfileEditViewPresenter
import net.craftal.web.usecase.artist.{GetArtistProfile, UpdateArtistProfile}
import net.craftal.web.usecase.genre.GetGenres
import net.craftal.web.usecase.prefecture.GetPrefectures
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc._

import scala.concurrent.ExecutionContext

class EditArtistProfileController @Inject()(controllerComponents: ControllerComponents,
                                            silhouette: Silhouette[DefaultEnv],
                                            getProfile: GetArtistProfile,
                                            updateProfile: UpdateArtistProfile,
                                            getPrefectures: GetPrefectures,
                                            getGenres: GetGenres,
                                            presenter: ArtistProfileEditViewPresenter)
                                           (implicit executionContext: ExecutionContext,
                                            webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    for {
      a <- this.getProfile.execute(request.identity.id)
      p <- this.getPrefectures.execute
      g <- this.getGenres.execute
    } yield Ok(presenter.present(EditArtistProfileForm.form, p, g, a._1))
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    EditArtistProfileForm.form.bindFromRequest.fold(
      form => for {
        a <- this.getProfile.execute(request.identity.id)
        p <- this.getPrefectures.execute
        g <- this.getGenres.execute
      } yield BadRequest(presenter.present(form, p, g, a._1)),
      data => this.updateProfile
        .execute(request.identity.id, Option(data.name), Option(data.prefectureId))
        .map(_ => Redirect(net.craftal.web.controller.mypage.routes.IndexController.view())
          .flashing("info" -> Messages("craftal.mypage.profile.message.edit.success", "RyosukeMurai"))
        )
    )
  }
}
