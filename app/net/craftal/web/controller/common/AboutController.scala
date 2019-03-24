package net.craftal.web.controller.common

import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import org.webjars.play.WebJarsUtil
import play.api.mvc._

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
