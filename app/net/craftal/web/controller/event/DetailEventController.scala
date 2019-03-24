package net.craftal.web.controller.event

import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.presenter.event.EventDetailViewPresenter
import net.craftal.web.usecase.event.GetEvent
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.ExecutionContext

class DetailEventController @Inject()(controllerComponents: ControllerComponents,
                                      actionWithNavigation: ActionWithNavigation,
                                      getEvent: GetEvent,
                                      presenter: EventDetailViewPresenter)
                                     (implicit executionContext: ExecutionContext,
                                      webJarsUtil: WebJarsUtil,
                                      assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(id: String): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[AnyContent] =>
    this.getEvent.execute(id.toInt).map { response => Ok(presenter.present(response._1, response._2)) }
  }
}
