package net.craftal.web.presenter.artist

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
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

  def present(values: (Artist, Genre, Prefecture, List[Photo]))
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.detail(
      ArtistDetailDataMapper.transform(values._1, values._2, values._3, values._4, List())
    )

  def present(artist: Artist, genre: Genre, prefecture: Prefecture, photos: List[Photo])
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.detail(
      ArtistDetailDataMapper.transform(artist, genre, prefecture, photos, List())
    )
}
