package net.craftal.web.presenter.auth

import controllers.AssetsFinder
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class ChangePasswordViewPresenter(implicit webJarsUtil: WebJarsUtil,
                                  assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[(String, String)])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.auth.html.changePassword(form, None /*request.identity*/)
}
