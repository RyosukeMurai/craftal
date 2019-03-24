package net.craftal.web.model.form.auth

import play.api.data.Form
import play.api.data.Forms._

/**
 * The `Forgot Password` form.
 */
object ForgotPasswordForm {

  /**
   * A play framework form.
   */
  val form = Form(
    "email" -> email
  )
}
