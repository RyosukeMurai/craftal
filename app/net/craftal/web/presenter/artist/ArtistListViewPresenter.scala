package net.craftal.web.presenter.artist

import controllers.AssetsFinder
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.photo.Photo
import net.craftal.web.mapper.ArtistListDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class ArtistListViewPresenter()(implicit webJarsUtil: WebJarsUtil,
                                assetsFinder: AssetsFinder) extends WebPresenter {

  def present(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.artist.html.list(None)

  def present(values: (List[Artist], List[Photo]))
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(values._1, values._2)

  def present(artists: List[Artist], photos: List[Photo])
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.artist.html.list(
      Option(ArtistListDataMapper.transform(artists, photos))
    )
}
