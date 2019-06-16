package net.craftal.web.model.form.artist

import play.api.data.Form
import play.api.data.Forms._

object SearchArtistForm {

  val form = Form(
    mapping(
      "keyword" -> optional(text)
    )(Data.apply)(Data.unapply)
  )

  case class Data(keyword: Option[String])

}
