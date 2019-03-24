package net.craftal.web.controller.common

import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.presenter.common.CommonViewPresenter
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.ExecutionContext

class FAQController @Inject()(controllerComponents: ControllerComponents,
                              actionWithNavigation: ActionWithNavigation,
                              presenter: CommonViewPresenter)
                             (implicit executionContext: ExecutionContext,
                              webJarsUtil: WebJarsUtil,
                              assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view: Action[AnyContent] = actionWithNavigation { implicit request: NavigationContext[AnyContent] =>
    Ok(presenter.presentFAQ)
  }

}
