package net.craftal.web.controller.mypage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.model.form.mypage.EditProfileForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.mypage.ProfileEditViewPresenter
import net.craftal.web.usecase.member.{GetMemberProfile, UpdateMemberProfile}
import net.craftal.web.usecase.prefecture.GetPrefectures
import net.craftal.web.usecase.profile.UpdateMemberProfile
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc._

import scala.concurrent.ExecutionContext

class EditProfileController @Inject()(controllerComponents: ControllerComponents,
                                      silhouette: Silhouette[DefaultEnv],
                                      getProfile: GetMemberProfile,
                                      updateProfile: UpdateMemberProfile,
                                      getPrefectures: GetPrefectures,
                                      presenter: ProfileEditViewPresenter)
                                     (implicit executionContext: ExecutionContext,
                                      webJarsUtil: WebJarsUtil,
                                      assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    this.getPrefectures
      .execute
      .zip(this.getProfile.execute(request.identity.id))
      .map(response => Ok(presenter.present(EditProfileForm.form, response._1, response._2)))
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    EditProfileForm.form.bindFromRequest.fold(
      form => this.getPrefectures.execute.map(response => BadRequest(presenter.present(form, response))),
      data => this.updateProfile
        .execute(request.identity.id, Option(data.name), Option(data.prefectureId))
        .map(_ => Redirect(net.craftal.web.controller.mypage.routes.IndexController.view())
          .flashing("info" -> Messages("craftal.mypage.profile.message.edit.success", "RyosukeMurai"))
        )
    )
  }
}
