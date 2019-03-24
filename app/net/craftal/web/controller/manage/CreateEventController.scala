package net.craftal.web.controller.manage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.model.form.event.CreateEventForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.manage.EventCreateViewPresenter
import net.craftal.web.usecase.event.CreateEvent
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class CreateEventController @Inject()(controllerComponents: ControllerComponents,
                                      silhouette: Silhouette[DefaultEnv],
                                      createEvent: CreateEvent,
                                      presenter: EventCreateViewPresenter)
                                     (implicit executionContext: ExecutionContext,
                                      webJarsUtil: WebJarsUtil,
                                      assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(presenter.present))
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    CreateEventForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      data => this.createEvent.execute(
        data.title,
        data.description,
        1,
        1,
      ).map(_ =>
        Redirect(web.controller.manage.routes.ListEventController.view())
          .flashing("info" -> Messages("craftal.management.event.message.create.success", data.title))
      )
    )
  }
}
