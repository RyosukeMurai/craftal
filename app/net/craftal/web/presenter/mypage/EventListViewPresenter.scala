package net.craftal.web.presenter.mypage

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.event.Event
import net.craftal.web.mapper.EventTableDataMapper
import net.craftal.web.model.common.Page
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class EventListViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                       assetsFinder: AssetsFinder) extends WebPresenter {

  def present(values: (List[Event], Int), page: Int, size: Int)(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(values._1, values._2, page, size)

  def present(events: List[Event], count: Int, page: Int, size: Int)(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.mypage.event.html.list(
      EventTableDataMapper.transform(events),
      Page(page, size, count)
    )
  }
}