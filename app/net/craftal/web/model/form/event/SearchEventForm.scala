package net.craftal.web.model.form.event

import play.api.data.Form
import play.api.data.Forms._

object SearchEventForm {

  val form = Form(
    mapping(
      "keyword" -> optional(text),
      "genre" -> optional(number),
    )(Data.apply)(Data.unapply)
  )

  case class Data(keyword: Option[String], genre: Option[Int])

}
