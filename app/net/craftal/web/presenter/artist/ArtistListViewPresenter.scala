package net.craftal.web.presenter.artist

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.controller.NavigationContext
import net.craftal.web.mapper.ArtistListDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.HtmlFormat

class ArtistListViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                        assetsFinder: AssetsFinder) extends WebPresenter {

  def present()(implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(List(), List(), List())

  def present(values: (List[Artist], List[Photo], List[Prefecture]))
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(values._1, values._2, values._3)

  def present(artists: List[Artist], photos: List[Photo], prefectures: List[Prefecture])
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.list(Option(ArtistListDataMapper.transform(artists, photos, prefectures)))
}
