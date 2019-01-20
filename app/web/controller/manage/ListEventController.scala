package web.controller.manage

import javax.inject._

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import useCase.event.{CountNumberOfEvents, GetEvents}
import web.DefaultEnv
import web.mapper.EventTableDataMapper
import web.model.common.Page

import scala.concurrent.ExecutionContext

class ListEventController @Inject()(controllerComponents: ControllerComponents,
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
