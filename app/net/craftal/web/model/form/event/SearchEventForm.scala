package net.craftal.web.model.form.event

import play.api.data.Form
import play.api.data.Forms._

object SearchEventForm {

  val form = Form(
    mapping(
      "keyword" -> optional(text)
    )(Data.apply)(Data.unapply)
  )

  case class Data(keyword: Option[String])

}
