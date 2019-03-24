package net.craftal.web.presenter.event

import controllers.AssetsFinder
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.web.mapper.EventCalendarDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class EventCalendarViewPresenter()(implicit webJarsUtil: WebJarsUtil,
                                   assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[_])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.event.html.calendar(form, None)

  def present(form: Form[_], values: (List[Event], List[Photo]))
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(form, values._1, values._2)

  def present(form: Form[_], events: List[Event], photos: List[Photo])
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.event.html.calendar(
      form,
      Option(EventCalendarDataMapper.transform(events, photos))
    )
}
