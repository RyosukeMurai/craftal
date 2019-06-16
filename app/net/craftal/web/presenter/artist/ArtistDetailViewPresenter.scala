package net.craftal.web.presenter.artist

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.controller.NavigationContext
import net.craftal.web.mapper.ArtistDetailDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.HtmlFormat

class ArtistDetailViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                          assetsFinder: AssetsFinder) extends WebPresenter {

  def present(values: (Artist, Genre, List[Prefecture], List[Photo], List[Event], List[Attribute]))
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.detail(
      ArtistDetailDataMapper.transform(values._1, values._2, values._3, values._4, values._5, values._6)
    )

  def present(artist: Artist, genre: Genre, prefectures: List[Prefecture], photos: List[Photo], events: List[Event], attributes: List[Attribute])
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.detail(
      ArtistDetailDataMapper.transform(artist, genre, prefectures, photos, events, attributes)
    )
}
