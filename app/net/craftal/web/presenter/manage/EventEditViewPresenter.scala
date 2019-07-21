package net.craftal.web.presenter.manage

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.event.Event
import net.craftal.web.model.form.event.{EditEventForm, EditEventFormDefinition}
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class EventEditViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                       assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[EditEventForm.Data])
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.manage.event.html.edit(
      new EditEventFormDefinition(form)
    )
  }

  def present(form: Form[EditEventForm.Data],
              event: Event)
             (implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    this.present(
      form.fill(EditEventForm.Data(event.title, event.description)),
    )
  }
}