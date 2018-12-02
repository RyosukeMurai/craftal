package web.controller.event

import controllers.AssetsFinder
import javax.inject._

import org.webjars.play.WebJarsUtil
import play.api.mvc._
import useCase.artist.GetArtistsParticipatingInEvent
import useCase.event.GetEvent
import web.action.ActionWithNavigation
import web.mapper.EventDetailDataMapper

import scala.concurrent.ExecutionContext

class DetailEventController @Inject()(controllerComponents: ControllerComponents,
                                      getEvent: GetEvent,
                                      getArtists: GetArtistsParticipatingInEvent)
                                     (implicit executionContext: ExecutionContext,
                                      webJarsUtil: WebJarsUtil,
                                      assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(id: String): Action[AnyContent] = ActionWithNavigation {
    Action.async { implicit request =>
      for {
        v <- getEvent.execute(id.toInt) zip getArtists.execute(id.toInt)
      } yield {
        Ok(
          web.view.event.html.detail(
            EventDetailDataMapper.transform(v._1, v._2)
          )
        )
      }
    }
  }
}
