package net.craftal.web.controller.artist

import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.model.form.artist.SearchArtistForm
import net.craftal.web.presenter.artist.ArtistListViewPresenter
import net.craftal.web.usecase.artist.GetArtistsOfGenre
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class ListArtistByGenreController @Inject()(controllerComponents: ControllerComponents,
                                            actionWithNavigation: ActionWithNavigation,
                                            getArtists: GetArtistsOfGenre,
                                            presenter: ArtistListViewPresenter)
                                           (implicit executionContext: ExecutionContext,
                                            webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(genreId: Int): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[AnyContent] =>
    SearchArtistForm.form.bindFromRequest(request.queryString).fold(
      _ => Future.successful(BadRequest(presenter.present)),
      data => getArtists.execute(genreId, data.keyword).map { response =>
        Ok(presenter.present(response))
      })
  }
}
