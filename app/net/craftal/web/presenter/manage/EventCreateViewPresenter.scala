package net.craftal.web.presenter.manage

import controllers.AssetsFinder
import net.craftal.web.model.form.event.{CreateEventForm, CreateEventFormDefinition}
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class EventCreateViewPresenter()(implicit webJarsUtil: WebJarsUtil,
                                 assetsFinder: AssetsFinder) extends WebPresenter {

  def present(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    web.view.manage.event.html.create(
      new CreateEventFormDefinition(None)
    )
  }


  def present(form: Form[CreateEventForm.Data])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    web.view.manage.event.html.create(
      new CreateEventFormDefinition(Option(form))
    )
  }
}