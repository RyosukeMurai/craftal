package net.craftal.web.presenter.event

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.controller.NavigationContext
import net.craftal.web.mapper.EventCalendarDataMapper
import net.craftal.web.model.form.event.SearchEventForm
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.HtmlFormat

class EventCalendarViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                           assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[SearchEventForm.Data])(implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.event.html.calendar(form, None)

  def present(form: Form[SearchEventForm.Data],
              values: (List[Event], List[Photo], List[Prefecture]),
              genres: List[Genre])
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(form, values._1, values._2, values._3, genres)

  def present(form: Form[SearchEventForm.Data],
              events: List[Event],
              photos: List[Photo],
              prefectures:List[Prefecture],
              genres:List[Genre])
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.event.html.calendar(
      form,
      Option(EventCalendarDataMapper.transform(events, photos, prefectures, genres)),
    )
}
