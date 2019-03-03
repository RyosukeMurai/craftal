package web.controller.artist

import javax.inject._
import controllers.AssetsFinder
import net.craftal.core.usecase.artist.GetArtist
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import web.action.{ActionWithNavigation, NavigationContext}
import web.mapper.ArtistDetailDataMapper

import scala.concurrent.ExecutionContext

@Singleton
class DetailArtistController @Inject()(controllerComponents: ControllerComponents,
                                       actionWithNavigation: ActionWithNavigation,
                                       getArtist: GetArtist)
                                      (implicit executionContext: ExecutionContext,
                                       webJarsUtil: WebJarsUtil,
                                       assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(id: String): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[_] =>
    for {
      v <- getArtist.execute(id.toInt)
    } yield {
      Ok(
        web.view.artist.html.detail(
          ArtistDetailDataMapper.transform(v, List(), List())
        )
      )
    }
  }
}
