package net.craftal.web.presenter.common

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.controller.NavigationContext
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.HtmlFormat

class CommonViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder) extends WebPresenter {

  def presentAbout(implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.about.html.view()

  def presentFAQ(implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.faq.html.view()
}
