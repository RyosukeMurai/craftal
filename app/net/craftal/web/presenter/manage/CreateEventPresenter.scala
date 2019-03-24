package net.craftal.web.presenter.manage

import controllers.AssetsFinder
import net.craftal.web.model.form.event.CreateEventFormDefinition
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

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
