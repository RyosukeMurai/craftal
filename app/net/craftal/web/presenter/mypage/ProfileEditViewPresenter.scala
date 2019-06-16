package net.craftal.web.presenter.mypage

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.member.Member
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.form.mypage.{EditProfileForm, EditProfileFormDefinition}
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class ProfileEditViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                         assetsFinder: AssetsFinder) extends WebPresenter {
  def present(form: Form[EditProfileForm.Data],
              prefectures: List[Prefecture])
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.mypage.profile.html.edit(
      new EditProfileFormDefinition(form, prefectures)
    )
  }

  def present(form: Form[EditProfileForm.Data],
              prefectures: List[Prefecture],
              member: Member)
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    this.present(
      form.fill(EditProfileForm.Data(member.name, member.email, member.prefectureId)),
      prefectures
    )
  }
}