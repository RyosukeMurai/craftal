package net.craftal.web.controller.mypage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.model.form.mypage.EditArtistProfileForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.mypage.ArtistProfileEditViewPresenter
import net.craftal.web.usecase.event.CreateEvent
import net.craftal.web.usecase.profile.GetArtistProfile
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class EditArtistProfileController @Inject()(controllerComponents: ControllerComponents,
                                            silhouette: Silhouette[DefaultEnv],
                                            getProfile: GetArtistProfile,
                                            editProfile: CreateEvent,
                                            presenter: ArtistProfileEditViewPresenter)
                                           (implicit executionContext: ExecutionContext,
                                            webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(presenter.present))
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    EditArtistProfileForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      data => Future.successful(Redirect(net.craftal.web.controller.mypage.routes.IndexController.view())
        .flashing("info" -> Messages("craftal.mypage.profile.message.edit.success", "RyosukeMurai"))
      )
    )
  }
}
