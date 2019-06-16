package net.craftal.web.model.form.mypage

import net.craftal.web.model.common.table.{FieldDefinition, FormDefinition}
import play.api.data.Form
import play.api.i18n.Messages

class EditArtistProfileFormDefinition(form: Option[Form[EditArtistProfileForm.Data]])(implicit messages: Messages) extends FormDefinition(
  fieldDefinitions = Seq(
    FieldDefinition("name", messages("craftal.mypage.profile.filed.name"), 1),
    FieldDefinition("email", messages("craftal.mypage.profile.filed.email"), 2),
    FieldDefinition("coverPhoto", messages("craftal.mypage.profile.filed.cover-photo"), 3),
    FieldDefinition("iconPhoto", messages("craftal.mypage.profile.filed.icon-photo"), 4),
    FieldDefinition("prefectureId", messages("craftal.mypage.profile.filed.prefecture"), 5),
    FieldDefinition("genreId", messages("craftal.mypage.profile.filed.genre"), 6),
    FieldDefinition("attributes", messages("craftal.mypage.profile.filed.attribute"), 7),
  ),
  form = form.getOrElse(EditArtistProfileForm.form),
  submitAction = net.craftal.web.controller.mypage.routes.EditArtistProfileController.submit(),
  cancelAction = net.craftal.web.controller.mypage.routes.IndexController.view(),
)
