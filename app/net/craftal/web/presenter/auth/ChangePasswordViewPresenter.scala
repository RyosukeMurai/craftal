package net.craftal.web.presenter.auth

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.port.silhouette.UserIdentity
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class ChangePasswordViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[(String, String)], identity: UserIdentity)(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.auth.html.changePassword(form, identity)
}
