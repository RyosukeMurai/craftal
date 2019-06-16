package net.craftal.web.controller.mypage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.mypage.EventListViewPresenter
import net.craftal.web.usecase.event.GetEventsForArtistParticipating
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.ExecutionContext

class ListEventController @Inject()(controllerComponents: ControllerComponents,
                                    silhouette: Silhouette[DefaultEnv],
                                    getEvents: GetEventsForArtistParticipating,
                                    presenter: EventListViewPresenter)
                                   (implicit executionContext: ExecutionContext,
                                    webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  //TODO(RyosukeMurai): move parameter to env
  val size: Int = 20

  def view(page: Int): Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    this.getEvents.execute(request.identity.id).map { response =>
      Ok(presenter.present((response._1, 10), page, size))
    }
  }
}
