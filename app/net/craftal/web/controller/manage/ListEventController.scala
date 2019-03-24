package net.craftal.web.controller.manage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.core.usecase.event.{CountNumberOfEvents, GetEvents}
import net.craftal.web.mapper.EventTableDataMapper
import net.craftal.web.model.common.Page
import net.craftal.web.port.silhouette.DefaultEnv
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.ExecutionContext

class
ListEventController @Inject()(controllerComponents: ControllerComponents,
                              getEvents: GetEvents,
                              countNumberOfEvents: CountNumberOfEvents,
                              silhouette: Silhouette[DefaultEnv])
                             (implicit executionContext: ExecutionContext,
                              webJarsUtil: WebJarsUtil,
                              assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  //TODO(RyosukeMurai): move parameter to env
  val size: Int = 20

  def view(page: Int): Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    for {
      e <- getEvents.execute()
      c <- countNumberOfEvents.execute()
    } yield {
      Ok(
        web.view.manage.event.html.list(
          EventTableDataMapper.transform(e),
          Page(page, size, c)
        )
      )
    }
  }
}
