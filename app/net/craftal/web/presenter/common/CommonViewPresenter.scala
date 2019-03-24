package net.craftal.web.presenter.common

import controllers.AssetsFinder
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class CommonViewPresenter()(implicit webJarsUtil: WebJarsUtil,
                            assetsFinder: AssetsFinder) extends WebPresenter {

  def presentAbout(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.about.html.view()

  def presentFAQ(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.faq.html.view()
}
