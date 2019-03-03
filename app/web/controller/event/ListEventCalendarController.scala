package web.controller.event

import java.util.Date
import javax.inject._

import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import usecase.event.GetEventsWithinPeriod
import usecase.genre.GetGenres
import usecase.photo.GetPhotosByIdList
import web.action.{ActionWithNavigation, NavigationContext}
import web.form.event.SearchEventForm
import web.mapper.EventCalendarDataMapper

import scala.concurrent.{ExecutionContext, Future}

class ListEventCalendarController @Inject()(controllerComponents: ControllerComponents,
                                            actionWithNavigation: ActionWithNavigation,
                                            getEvents: GetEventsWithinPeriod,
                                            getPhotos: GetPhotosByIdList,
                                            getGenres: GetGenres)
                                           (implicit executionContext: ExecutionContext,
                                            webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[_] =>
    SearchEventForm.form.bindFromRequest(request.queryString).fold(
      form => Future.successful(BadRequest(web.view.event.html.listCalendar(form, None))),
      data => for {
        e <- getEvents.execute(new Date(), None, Option(data.keyword))
        p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
      } yield {
        Ok(
          web.view.event.html.listCalendar(
            SearchEventForm.form,
            Option(EventCalendarDataMapper.transform(e, p))
          )
        )
      })
  }
}
