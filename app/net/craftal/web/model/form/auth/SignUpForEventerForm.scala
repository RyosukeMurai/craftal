package net.craftal.web.model.form.auth

import play.api.data.Form
import play.api.data.Forms._

object SignUpForEventerForm {

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

  case class Data(name: String,
                  email: String,
                  password: String)

}
