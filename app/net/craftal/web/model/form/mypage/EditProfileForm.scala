package net.craftal.web.model.form.mypage

import play.api.data.Form
import play.api.data.Forms._

object EditProfileForm {

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "prefectureId" -> number
    )(Data.apply)(Data.unapply)
  )

  case class Data(name: String,
                  email: String,
                  prefectureId: Int)

}
