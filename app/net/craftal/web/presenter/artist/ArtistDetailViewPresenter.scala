package net.craftal.web.presenter.artist

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.web.controller.NavigationContext
import net.craftal.web.mapper.ArtistDetailDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.HtmlFormat

class ArtistDetailViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                          assetsFinder: AssetsFinder) extends WebPresenter {

  def present(values: (Artist, Genre))
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.detail(
      ArtistDetailDataMapper.transform(values._1, values._2, List(), List())
    )

  def present(artist: Artist, genre: Genre)
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.detail(
      ArtistDetailDataMapper.transform(artist, genre, List(), List())
    )
}
