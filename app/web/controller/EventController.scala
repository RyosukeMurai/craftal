package web.controller


import java.util.Date

import controllers.AssetsFinder
import javax.inject._
import org.webjars.play.WebJarsUtil
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import useCase.artist.GetArtistsParticipatingInEvent
import useCase.event.{GetEvent, GetEventsWithinPeriod}
import useCase.genre.GetGenres
import useCase.photo.GetPhotosByIdList
import web.model.event.EventSearchCondition
import web.model.mapper.{EventCalendarDataMapper, EventDetailDataMapper}

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class EventController @Inject()(controllerComponents: ControllerComponents,
                                getEvents: GetEventsWithinPeriod,
                                getEvent: GetEvent,
                                getArtists: GetArtistsParticipatingInEvent,
                                getPhotos: GetPhotosByIdList,
                                getGenres: GetGenres)
                               (implicit executionContext: ExecutionContext, webJarsUtil: WebJarsUtil, assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index: Action[AnyContent] = Action.async { implicit request =>
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

  def detail(id: String): Action[AnyContent] = Action.async { implicit request =>
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
