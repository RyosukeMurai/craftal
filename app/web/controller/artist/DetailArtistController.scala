package web.controller.artist

import controllers.AssetsFinder
import javax.inject._
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import useCase.artist.GetArtist
import web.mapper.ArtistDetailDataMapper

import scala.concurrent.ExecutionContext

@Singleton
class DetailArtistController @Inject()(controllerComponents: ControllerComponents,
                                       getArtist: GetArtist)
                                      (implicit executionContext: ExecutionContext,
                                       webJarsUtil: WebJarsUtil,
                                       assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(id: String): Action[AnyContent] = Action.async { implicit request =>
    for {
      v <- getArtist.execute(id.toInt)
    } yield {
      Ok(
        web.view.artist.html.detail(
          ArtistDetailDataMapper.transform(v, List())
        )
      )
    }
  }
}
