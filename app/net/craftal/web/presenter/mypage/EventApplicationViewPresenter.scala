package net.craftal.web.presenter.mypage

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.event.{ApplyEventForm, ApplyEventFormDefinition}
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class EventApplicationViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                              assetsFinder: AssetsFinder) extends WebPresenter {

  def present(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.mypage.event.html.apply(
      new ApplyEventFormDefinition(None)
    )
  }


  def present(form: Form[ApplyEventForm.Data])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable = {
    net.craftal.web.view.mypage.event.html.apply(
      new ApplyEventFormDefinition(Option(form))
    )
  }
}