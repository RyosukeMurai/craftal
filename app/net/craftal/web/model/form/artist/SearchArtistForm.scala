package net.craftal.web.model.form.artist

import play.api.data.Form
import play.api.data.Forms._

object SearchArtistForm {

  val form = Form(
    mapping(
      "keyword" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

  case class Data(keyword: String)

}
