package web.presenter.manage

import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat
import web.form.event.CreateEventFormDefinition
import web.presenter.WebPresenter

import scala.concurrent.ExecutionContext

class CreateEventPresenter()(implicit executionContext: ExecutionContext,
                             webJarsUtil: WebJarsUtil,
                             assetsFinder: AssetsFinder) extends WebPresenter {

  def presentViewResult()(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    web.view.manage.event.html.create(
      new CreateEventFormDefinition(None)
    )
  }

  def presentSubmitResult()(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    web.view.manage.event.html.create(
      new CreateEventFormDefinition(None)
    )
  }
}
