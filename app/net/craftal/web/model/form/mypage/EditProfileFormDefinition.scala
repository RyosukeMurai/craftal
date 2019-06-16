package net.craftal.web.model.form.mypage

import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.common.table.{FieldDefinition, FieldType, FormDefinition}
import play.api.data.Form
import play.api.i18n.Messages

class EditProfileFormDefinition(form: Form[EditProfileForm.Data],
                                prefectures: Seq[Prefecture])
                               (implicit messages: Messages) extends FormDefinition(
  fieldDefinitions = Seq(
    FieldDefinition("name", messages("craftal.mypage.profile.filed.name"), 1),
    FieldDefinition("email", messages("craftal.mypage.profile.filed.email"), 2),
    FieldDefinition(
      "prefectureId",
      messages("craftal.mypage.profile.filed.prefecture"),
      3,
      FieldType.select,
      Option(prefectures.map(p => (p.id.toString, p.name)))
    )
  ),
  form = form,
  submitAction = net.craftal.web.controller.mypage.routes.EditProfileController.submit(),
  cancelAction = net.craftal.web.controller.mypage.routes.IndexController.view(),
)
