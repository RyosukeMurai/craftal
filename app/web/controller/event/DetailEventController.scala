package web.controller.event

import javax.inject._

import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import useCase.artist.GetArtistsParticipatingInEvent
import useCase.event.GetEvent
import web.action.{ActionWithNavigation, NavigationContext}
import web.mapper.EventDetailDataMapper

import scala.concurrent.ExecutionContext

class DetailEventController @Inject()(controllerComponents: ControllerComponents,
                                      actionWithNavigation: ActionWithNavigation,
                                      getEvent: GetEvent,
                                      getArtists: GetArtistsParticipatingInEvent)
                                     (implicit executionContext: ExecutionContext,
                                      webJarsUtil: WebJarsUtil,
                                      assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(id: String): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[_] =>
    for {
      v <- getEvent.execute(id.toInt) zip getArtists.execute(id.toInt)
    } yield {
      Ok(
        web.view.event.html.detail(
          EventDetailDataMapper.transform(v._1, Seq(), v._2)
        )
      )
    }
  }
}
