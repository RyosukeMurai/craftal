package net.craftal.web.controller.artist

import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.presenter.artist.ArtistDetailViewPresenter
import net.craftal.web.usecase.artist.GetArtist
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.ExecutionContext

class DetailArtistController @Inject()(controllerComponents: ControllerComponents,
                                       actionWithNavigation: ActionWithNavigation,
                                       getArtist: GetArtist,
                                       presenter: ArtistDetailViewPresenter)
                                      (implicit executionContext: ExecutionContext,
                                       webJarsUtil: WebJarsUtil,
                                       assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(id: String): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[AnyContent] =>
    getArtist.execute(id.toInt).map { response =>
      Ok(presenter.present(response))
    }
  }
}
