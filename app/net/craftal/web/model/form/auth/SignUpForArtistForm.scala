package net.craftal.web.model.form.auth

import play.api.data.Form
import play.api.data.Forms._

object SignUpForArtistForm {

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText,
      "prefectureId" -> number,
      "genreId" -> number,
    )(Data.apply)(Data.unapply)
  )

  case class Data(name: String,
                  email: String,
                  password: String,
                  prefectureId: Int,
                  genreId: Int)

}
