package net.craftal.web.presenter.artist

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.web.controller.NavigationContext
import net.craftal.web.mapper.ArtistSummaryDataMapper
import net.craftal.web.model.form.artist.SearchArtistForm
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.HtmlFormat

class ArtistSummaryViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                           assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[SearchArtistForm.Data])(implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.summary(form, None)

  def present(form: Form[SearchArtistForm.Data], values: (List[Artist], List[Genre]))
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    this.present(form, values._1, values._2)

  def present(form: Form[SearchArtistForm.Data], artists: List[Artist], genres: List[Genre])
             (implicit request: NavigationContext[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.artist.html.summary(
      form,
      Option(ArtistSummaryDataMapper.transform(artists, genres))
    )
}
