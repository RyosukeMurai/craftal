package web.controller.common

import controllers.AssetsFinder
import javax.inject._

import org.webjars.play.WebJarsUtil
import play.api.mvc._
import web.action.{ActionWithNavigation, NavigationContext}

import scala.concurrent.ExecutionContext

class FAQController @Inject()(controllerComponents: ControllerComponents,
                              actionWithNavigation: ActionWithNavigation)
                             (implicit executionContext: ExecutionContext,
                              webJarsUtil: WebJarsUtil,
                              assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view: Action[AnyContent] = actionWithNavigation { implicit request: NavigationContext[_] =>
    Ok(web.view.faq.html.view())
  }

}
