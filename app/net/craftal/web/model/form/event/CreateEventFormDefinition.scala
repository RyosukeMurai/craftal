package net.craftal.web.model.form.event

import net.craftal.web.model.common.table.{FieldDefinition, FormDefinition}
import play.api.data.Form
import play.api.i18n.Messages

class CreateEventFormDefinition(form: Option[Form[CreateEventForm.Data]])(implicit messages: Messages) extends FormDefinition(
  fieldDefinitions = Seq(
    FieldDefinition("title", messages("craftal.management.event.filed.title"), 1),
    FieldDefinition("description", messages("craftal.management.event.filed.description"), 2),
  ),
  form = form.getOrElse(CreateEventForm.form),
  submitAction = net.craftal.web.controller.manage.routes.CreateEventController.submit(),
  cancelAction = net.craftal.web.controller.manage.routes.ListEventController.view(),
)
