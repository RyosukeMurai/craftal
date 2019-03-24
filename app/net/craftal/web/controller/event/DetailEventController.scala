package net.craftal.web.controller.event

import controllers.AssetsFinder
import javax.inject._
import net.craftal.core.usecase.artist.GetArtistsParticipatingInEvent
import net.craftal.core.usecase.event.GetEvent
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.mapper.EventDetailDataMapper
import org.webjars.play.WebJarsUtil
import play.api.mvc._

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
      v <- getEvent.execute(id.toInt) zip getArtists.execute(id.toInt, None)
    } yield {
      Ok(
        web.view.event.html.detail(
          EventDetailDataMapper.transform(v._1, Seq(), v._2)
        )
      )
    }
  }
}
