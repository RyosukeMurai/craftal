package net.craftal.web.model.form.event

import net.craftal.web.model.common.table.{FieldDefinition, FormDefinition}
import play.api.data.Form
import play.api.i18n.Messages

class ApplyEventFormDefinition(form: Option[Form[ApplyEventForm.Data]])(implicit messages: Messages) extends FormDefinition(
  fieldDefinitions = Seq(
    FieldDefinition("title", messages("craftal.mypage.event.filed.title"), 1),
    FieldDefinition("description", messages("craftal.mypage.event.filed.description"), 2),
  ),
  form = form.getOrElse(ApplyEventForm.form),
  submitAction = net.craftal.web.controller.mypage.routes.ApplyEventController.submit(),
  cancelAction = net.craftal.web.controller.mypage.routes.ListEventController.view(),
)
