package web.controller.manage

import controllers.AssetsFinder
import javax.inject._

import org.webjars.play.WebJarsUtil
import play.api.mvc._
import useCase.event.{CountNumberOfEvents, GetEvents}
import web.mapper.EventTableDataMapper
import web.model.common.Page

import scala.concurrent.ExecutionContext

class EventListController @Inject()(controllerComponents: ControllerComponents,
                                    getEvents: GetEvents,
                                    countNumberOfEvents: CountNumberOfEvents)
                                   (implicit executionContext: ExecutionContext,
                                    webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  //TODO(RyosukeMurai): move parameter to env
  val size: Int = 20

  def view(page: Int): Action[AnyContent] = Action.async { implicit request =>
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
