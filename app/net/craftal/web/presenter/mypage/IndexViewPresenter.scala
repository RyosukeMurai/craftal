package net.craftal.web.presenter.mypage

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class IndexViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                   assetsFinder: AssetsFinder) extends WebPresenter {

  def present(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.mypage.html.index()
  }
}