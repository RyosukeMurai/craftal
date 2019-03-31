package net.craftal.web.presenter.auth

import java.util.UUID

import controllers.AssetsFinder
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class ResetPasswordViewPresenter(implicit webJarsUtil: WebJarsUtil,
                                 assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[String], token: UUID)(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.auth.html.resetPassword(form, token)
}
