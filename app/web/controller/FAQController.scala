package web.controller

import controllers.AssetsFinder
import javax.inject._
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class FAQController @Inject()(controllerComponents: ControllerComponents)
                             (implicit executionContext: ExecutionContext, webJarsUtil: WebJarsUtil, assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def index: Action[AnyContent] = Action { implicit request =>
    Ok(web.view.faq.html.index())
  }
}
