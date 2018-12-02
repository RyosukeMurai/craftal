package web.model.form.auth

import play.api.data.Form
import play.api.data.Forms._

/**
 * The `Reset Password` form.
 */
object ResetPasswordForm {

  /**
   * A play framework form.
   */
  val form = Form(
    "password" -> nonEmptyText
  )
}
