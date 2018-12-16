package web.controller.common

import javax.inject._

import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import web.action.{ActionWithNavigation, NavigationContext}

import scala.concurrent.{ExecutionContext, Future}

class AboutController @Inject()(controllerComponents: ControllerComponents,
                                actionWithNavigation: ActionWithNavigation)
                               (implicit executionContext: ExecutionContext,
                                webJarsUtil: WebJarsUtil,
                                assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view: Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[AnyContent] =>
    Future.successful {
      Ok(web.view.about.html.view())
    }
  }

}
