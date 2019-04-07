package net.craftal.web.presenter.event

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.web.mapper.EventDetailDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class EventDetailViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                         assetsFinder: AssetsFinder) extends WebPresenter {

  def present(event: Event, artists: List[Artist])
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.event.html.detail(
      EventDetailDataMapper.transform(event, Seq(), artists)
    )
}
