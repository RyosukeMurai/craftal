package net.craftal.web.presenter.auth

import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.mapper.SignUpDataMapper
import net.craftal.web.model.form.auth.SignUpForm
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class SignUpViewPresenter @Inject()(implicit webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[SignUpForm.Data],
              prefectures: List[Prefecture])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    net.craftal.web.view.auth.html.signUp(form, SignUpDataMapper.transform(prefectures))

}
