package net.craftal.web.model.form.auth

import play.api.data.Form
import play.api.data.Forms._

/**
  * The `Change Password` form.
  */
object ChangePasswordForm {

  /**
    * A play framework form.
    */
  val form = Form(tuple(
    "current-password" -> nonEmptyText,
    "new-password" -> nonEmptyText
  ))
}
