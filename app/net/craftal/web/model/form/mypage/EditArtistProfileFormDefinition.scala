package net.craftal.web.model.form.mypage

import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.common.table.{FieldDefinition, FieldType, FormDefinition}
import play.api.data.Form
import play.api.i18n.Messages

class EditArtistProfileFormDefinition(form: Form[EditArtistProfileForm.Data],
                                      prefectures: Seq[Prefecture],
                                      genres: Seq[Genre])(implicit messages: Messages) extends FormDefinition(
  fieldDefinitions = Seq(
    FieldDefinition("name", messages("craftal.mypage.profile.filed.name"), 1),
    FieldDefinition("email", messages("craftal.mypage.profile.filed.email"), 2),
    FieldDefinition("coverPhoto", messages("craftal.mypage.profile.filed.cover-photo"), 3, FieldType.file),
    FieldDefinition("iconPhoto", messages("craftal.mypage.profile.filed.icon-photo"), 4),
    FieldDefinition(
      "prefectureId",
      messages("craftal.mypage.profile.filed.prefecture"),
      5,
      FieldType.select,
      Option(prefectures.map(p => (p.id.toString, p.name)))
    ),
    FieldDefinition(
      "genreId",
      messages("craftal.mypage.profile.filed.genre"),
      5,
      FieldType.select,
      Option(genres.map(g => (g.id.toString, g.name)))
    ),
    FieldDefinition("attributes", messages("craftal.mypage.profile.filed.attribute"), 7),
  ),
  form = form,
  submitAction = net.craftal.web.controller.mypage.routes.EditArtistProfileController.submit(),
  cancelAction = net.craftal.web.controller.mypage.routes.IndexController.view(),
)
