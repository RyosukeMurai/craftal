package web.model.form

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import web.model.{FieldDefinition, FormDefinition}

object CreateEventForm {

  val form = Form(
    mapping(
      "title" -> nonEmptyText,
      "description" -> nonEmptyText,
    )(Data.apply)(Data.unapply)
  )

  case class Data(title: String,
                  description: String)

}

class CreateEventFormDefinition()(implicit messages: Messages) extends FormDefinition(
  fieldDefinitions = Seq(
    FieldDefinition("title", messages("craftal.management.event.filed.title"), 1),
    FieldDefinition("description", messages("craftal.management.event.filed.description"), 2),
  ),
  form = CreateEventForm.form,
  submitAction = web.controller.manage.routes.CreateEventController.view(),
  cancelAction = web.controller.manage.routes.CreateEventController.view(),
)
