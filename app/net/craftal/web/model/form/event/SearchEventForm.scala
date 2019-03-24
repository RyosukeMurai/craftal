package net.craftal.web.model.form.event

import play.api.data.Form
import play.api.data.Forms._

object SearchEventForm {

  val form = Form(
    mapping(
      "keyword" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

  case class Data(keyword: String)

}
