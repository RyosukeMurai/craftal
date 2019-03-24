package net.craftal.web.controller.event

import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.model.form.event.SearchEventForm
import net.craftal.web.presenter.event.EventCalendarViewPresenter
import net.craftal.web.usecase.event.GetEventCalendar
import org.joda.time.DateTime
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class ListEventCalendarController @Inject()(controllerComponents: ControllerComponents,
                                            actionWithNavigation: ActionWithNavigation,
                                            getEvents: GetEventCalendar,
                                            presenter: EventCalendarViewPresenter)
                                           (implicit executionContext: ExecutionContext,
                                            webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[AnyContent] =>
    SearchEventForm.form.bindFromRequest(request.queryString).fold(
      form => Future.successful(BadRequest(presenter.present(form))),
      data => this.getEvents.execute(new DateTime(), None, Option(data.keyword)).map { response =>
        Ok(presenter.present(SearchEventForm.form, response))
      }
    )
  }
}
