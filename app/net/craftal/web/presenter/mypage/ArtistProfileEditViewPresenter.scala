package net.craftal.web.presenter.mypage

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.form.mypage.{EditArtistProfileForm, EditArtistProfileFormDefinition}
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class ArtistProfileEditViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                               assetsFinder: AssetsFinder) extends WebPresenter {
  def present(form: Form[EditArtistProfileForm.Data],
              prefectures: List[Prefecture],
              genres: List[Genre])
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.mypage.profile.html.edit(
      new EditArtistProfileFormDefinition(form, prefectures, genres)
    )
  }

  def present(form: Form[EditArtistProfileForm.Data],
              prefectures: List[Prefecture],
              genres: List[Genre],
              artist: Artist)
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    this.present(
      form.fill(
        EditArtistProfileForm.Data(
          artist.name,
          artist.email,
          0,
          0,
          artist.prefectureId,
          artist.genreId,
          Seq()
        )
      ),
      prefectures,
      genres
    )
  }
}