package net.craftal.web.presenter.mypage

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.mapper.MypageDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class IndexViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                   assetsFinder: AssetsFinder) extends WebPresenter {

  def present(values: (List[Artist], List[Event], List[Photo], List[Genre], List[Prefecture]))
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(values._1, values._2, values._3, values._4, values._5)

  def present(artists: List[Artist], events: List[Event], photos: List[Photo], genres: List[Genre], prefectures: List[Prefecture])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.mypage.html.index(
      MypageDataMapper.transform(artists, events, photos, prefectures)
    )
  }
}