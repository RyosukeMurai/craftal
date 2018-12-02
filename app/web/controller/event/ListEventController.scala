package web.controller.event

import java.util.Date

import controllers.AssetsFinder
import javax.inject._

import org.webjars.play.WebJarsUtil
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import useCase.event.GetEventsWithinPeriod
import useCase.genre.GetGenres
import useCase.photo.GetPhotosByIdList
import web.action.ActionWithNavigation
import web.mapper.EventCalendarDataMapper
import web.model.event.EventSearchCondition

import scala.concurrent.ExecutionContext

class ListEventController @Inject()(controllerComponents: ControllerComponents,
                                    getEvents: GetEventsWithinPeriod,
                                    getPhotos: GetPhotosByIdList,
                                    getGenres: GetGenres)
                                   (implicit executionContext: ExecutionContext,
                                    webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view: Action[AnyContent] = ActionWithNavigation {
    Action.async { implicit request =>
      val searchForm = Form(mapping("keyword" -> text)(EventSearchCondition.apply)(EventSearchCondition.unapply))
      val condition = searchForm.bindFromRequest(request.queryString).value
      for {
        g <- getGenres.execute()
        e <- getEvents.execute(new Date(), None, condition)
        p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
      } yield {
        Ok(
          web.view.event.html.index(
            EventCalendarDataMapper.transform(e, p),
            g,
            searchForm
          )
        )
      }
    }
  }
}
