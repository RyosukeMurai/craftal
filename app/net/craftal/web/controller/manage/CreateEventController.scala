package net.craftal.web.controller.manage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.core.domain.model.event.{EventLocation, EventStatus}
import net.craftal.core.usecase.event.CreateEvent
import net.craftal.web.model.form.event.{CreateEventForm, CreateEventFormDefinition}
import net.craftal.web.port.silhouette.DefaultEnv
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc._

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
      data => this.createEvent.execute(
        data.title,
        data.description,
        EventStatus.draft,
        EventLocation.indoor
      ).map(_ =>
        Redirect(web.controller.manage.routes.ListEventController.view())
          .flashing("info" -> Messages("craftal.management.event.message.create.success", data.title))
      )
    )
  }
}
