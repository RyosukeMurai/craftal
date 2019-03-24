package net.craftal.web.presenter.auth

import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.model.form.auth.SignInForm
import net.craftal.web.presenter.WebPresenter
import org.webjars.play.WebJarsUtil
import play.api.data.Form
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}
import play.twirl.api.HtmlFormat

class SignInViewPresenter @Inject()(socialProviderRegistry: SocialProviderRegistry)
                                   (implicit webJarsUtil: WebJarsUtil,
                                    assetsFinder: AssetsFinder) extends WebPresenter {

  def present(form: Form[SignInForm.Data])(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.auth.html.signIn(form, socialProviderRegistry)

  def presentActivate(email: String)(implicit request: Request[AnyContent], messages: Messages): HtmlFormat.Appendable =
    web.view.auth.html.activateAccount(email)

}
