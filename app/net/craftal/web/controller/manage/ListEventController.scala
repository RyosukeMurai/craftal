package net.craftal.web.controller.manage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.manage.EventListViewPresenter
import net.craftal.web.usecase.event.GetEvents
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.ExecutionContext

class ListEventController @Inject()(controllerComponents: ControllerComponents,
                                    silhouette: Silhouette[DefaultEnv],
                                    getEvents: GetEvents,
                                    presenter: EventListViewPresenter)
                                   (implicit executionContext: ExecutionContext,
                                    webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  //TODO(RyosukeMurai): move parameter to env
  val size: Int = 20

  def view(page: Int): Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    this.getEvents.execute.map { response =>
      Ok(presenter.present(response, page, size))
    }
  }
}
