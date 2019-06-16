package net.craftal.web.model.form.mypage

import play.api.data.Form
import play.api.data.Forms._

object EditArtistProfileForm {

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "coverPhoto" -> byteNumber,
      "iconPhoto" -> byteNumber,
      "prefectureId" -> number,
      "genreId" -> number,
      "attributes" -> seq(number)
    )(Data.apply)(Data.unapply)
  )

  case class Data(name: String,
                  email: String,
                  coverPhoto: Byte,
                  iconPhoto: Byte,
                  prefectureId: Int,
                  genreId: Int,
                  attributes: Seq[Int])

}
