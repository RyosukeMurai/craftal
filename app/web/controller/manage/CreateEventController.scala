package web.controller.manage

import controllers.AssetsFinder
import javax.inject._
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import web.form.auth.CreateEventFormDefinition

import scala.concurrent.{ExecutionContext, Future}

class CreateEventController @Inject()(controllerComponents: ControllerComponents)
                                     (implicit executionContext: ExecutionContext,
                                      webJarsUtil: WebJarsUtil,
                                      assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(
      Ok(
        web.view.manage.event.html.create(
          new CreateEventFormDefinition()
        )
      )
    )
  }
}
