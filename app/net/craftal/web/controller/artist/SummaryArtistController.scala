package net.craftal.web.controller.artist

import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.model.form.artist.SearchArtistForm
import net.craftal.web.presenter.artist.ArtistSummaryViewPresenter
import net.craftal.web.usecase.artist.GetArtists
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class SummaryArtistController @Inject()(controllerComponents: ControllerComponents,
                                        actionWithNavigation: ActionWithNavigation,
                                        getArtists: GetArtists,
                                        presenter: ArtistSummaryViewPresenter)
                                       (implicit executionContext: ExecutionContext,
                                        webJarsUtil: WebJarsUtil,
                                        assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[AnyContent] =>
    SearchArtistForm.form.bindFromRequest(request.queryString).fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      data => getArtists.execute(Option(data.keyword)).map { response =>
        Ok(presenter.present(SearchArtistForm.form, response))
      })
  }
}
