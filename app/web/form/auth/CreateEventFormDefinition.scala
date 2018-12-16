package web.form.auth

import play.api.i18n.Messages
import web.model.common.table.{FieldDefinition, FormDefinition}

class CreateEventFormDefinition()(implicit messages: Messages) extends FormDefinition(
  fieldDefinitions = Seq(
    FieldDefinition("title", messages("craftal.management.event.filed.title"), 1),
    FieldDefinition("description", messages("craftal.management.event.filed.description"), 2),
  ),
  form = CreateEventForm.form,
  submitAction = web.controller.manage.routes.CreateEventController.view(),
  cancelAction = web.controller.manage.routes.CreateEventController.view(),
)
