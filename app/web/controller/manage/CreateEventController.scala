package web.controller.manage

import javax.inject._
import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc._
import useCase.event.CreateEvent
import useCase.event.request.CreateEventRequest
import web.form.event.{CreateEventForm, CreateEventFormDefinition}
import web.silhouette.DefaultEnv

import scala.concurrent.{ExecutionContext, Future}

class CreateEventController @Inject()(controllerComponents: ControllerComponents,
                                      createEvent: CreateEvent,
                                      silhouette: Silhouette[DefaultEnv])
                                     (implicit executionContext: ExecutionContext,
                                      webJarsUtil: WebJarsUtil,
                                      assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(
      Ok(
        web.view.manage.event.html.create(
          new CreateEventFormDefinition(None)
        )
      )
    )
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    CreateEventForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(web.view.manage.event.html.create(new CreateEventFormDefinition(Option(form))))),
      data => this.createEvent.execute(CreateEventRequest(data.title, data.description, 1, 1)).map(_ =>
        Redirect(web.controller.manage.routes.ListEventController.view())
          .flashing("info" -> Messages("craftal.management.event.message.create.success", data.title))
      )
    )
  }
}
