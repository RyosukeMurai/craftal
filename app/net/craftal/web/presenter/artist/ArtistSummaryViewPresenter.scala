package net.craftal.web.presenter.artist

import controllers.AssetsFinder
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.web.mapper.ArtistSummaryDataMapper
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class ArtistSummaryViewPresenter()(implicit webJarsUtil: WebJarsUtil,
                                   assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[Any])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.artist.html.summary(form, None)

  def present(form: Form[Any], values: (List[Artist], List[Genre]))
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(form, values._1, values._2)

  def present(form: Form[Any], artists: List[Artist], genres: List[Genre])
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.artist.html.summary(
      form,
      Option(ArtistSummaryDataMapper.transform(artists, genres))
    )
}
