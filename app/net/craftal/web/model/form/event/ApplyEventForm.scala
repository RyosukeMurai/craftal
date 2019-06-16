package net.craftal.web.model.form.event

import play.api.data.Form
import play.api.data.Forms._

object ApplyEventForm {

  val form = Form(
    mapping(
      "title" -> nonEmptyText,
      "description" -> nonEmptyText,
    )(Data.apply)(Data.unapply)
  )

  case class Data(title: String,
                  description: String)

}
