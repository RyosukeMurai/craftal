package net.craftal.web.controller.manage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.model.form.event.EditEventForm
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.manage.EventEditViewPresenter
import net.craftal.web.usecase.event.{GetEvent, UpdateEvent}
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc._

import scala.concurrent.ExecutionContext

class EditEventController @Inject()(controllerComponents: ControllerComponents,
                                    silhouette: Silhouette[DefaultEnv],
                                    getEvent: GetEvent,
                                    updateEvent: UpdateEvent,
                                    presenter: EventEditViewPresenter)
                                   (implicit executionContext: ExecutionContext,
                                    webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    this.getEvent.execute(1).map(response => Ok(presenter.present(EditEventForm.form, response._1)))
  }

  def submit: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: Request[AnyContent] =>
    EditEventForm.form.bindFromRequest.fold(
      form => this.getEvent.execute(1).map(response => BadRequest(presenter.present(form, response._1))),
      data => this.updateEvent
        .execute(
          1,
          Option(data.title),
          Option(data.description)
        )
        .map(_ => Redirect(net.craftal.web.controller.manage.routes.ListEventController.view())
          .flashing("info" -> Messages("craftal.management.event.message.event.success", data.title))
        )
    )
  }
}
