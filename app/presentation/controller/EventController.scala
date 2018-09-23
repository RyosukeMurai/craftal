package presentation.controller


import java.util.Date

import controllers.AssetsFinder
import domain.event.interactor.{GetEvent, GetEventsWithinPeriod}
import javax.inject._
import play.api.mvc._
import presentation.mapper.EventCalendarDataMapper

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class EventController @Inject()(cc: ControllerComponents,
                                getEvents: GetEventsWithinPeriod,
                                getEvent: GetEvent)(implicit assetsFinder: AssetsFinder, ec: ExecutionContext)
  extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index: Action[AnyContent] = Action.async {
    getEvents.execute(new Date(), None).map {
      events => {
        Ok(presentation.view.event.html.index(EventCalendarDataMapper.transform(events)))
      }
    }
  }

  def detail(id: String): Action[AnyContent] = Action.async {
    getEvent.execute(id.toInt).map {
      event => Ok(presentation.view.event.html.detail(event))
    }
  }
}
