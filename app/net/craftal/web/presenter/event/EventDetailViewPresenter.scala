package net.craftal.web.presenter.event

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.controller.NavigationContext
import net.craftal.web.mapper.EventDetailDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.HtmlFormat

class EventDetailViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                         assetsFinder: AssetsFinder) extends WebPresenter {

  def present(values: (Event, List[Photo], List[Prefecture], List[Artist], List[Attribute]))
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(values._1, values._2, values._3, values._4, values._5)

  def present(event: Event, photos: List[Photo], prefectures: List[Prefecture], artists: List[Artist], attributes: List[Attribute])
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.event.html.detail(
      EventDetailDataMapper.transform(event, photos, prefectures, artists, attributes)
    )
}
