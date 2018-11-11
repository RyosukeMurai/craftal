package web.controller

import auth.service.UserIdentityService
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import controllers.AssetsFinder
import javax.inject.Inject
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.libs.mailer.MailerClient
import play.api.mvc._
import web.DefaultEnv
import web.model.form.ForgotPasswordForm

import scala.concurrent.{ExecutionContext, Future}

class ForgotPasswordController @Inject()(
                                          components: ControllerComponents,
                                          silhouette: Silhouette[DefaultEnv],
                                          userService: UserIdentityService,
                                          mailerClient: MailerClient
                                        )(
                                          implicit
                                          webJarsUtil: WebJarsUtil,
                                          assets: AssetsFinder,
                                          ex: ExecutionContext
                                        ) extends AbstractController(components) with I18nSupport {

  /**
    * Views the `Forgot Password` page.
    *
    * @return The result to display.
    */
  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(web.view.auth.html.forgotPassword(ForgotPasswordForm.form)))
  }

  /**
    * Sends an email with password reset instructions.
    *
    * It sends an email to the given address if it exists in the database. Otherwise we do not show the user
    * a notice for not existing email addresses to prevent the leak of existing email addresses.
    *
    * @return The result to display.
    */
  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    ForgotPasswordForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(web.view.auth.html.forgotPassword(form))),
      email => {
        val loginInfo = LoginInfo(CredentialsProvider.ID, email)
        val result = Redirect(routes.SignInController.view()).flashing("info" -> Messages("reset.email.sent"))
        /*
        userService.retrieve(loginInfo).flatMap {
          case Some(user) if user.email.isDefined =>
            authTokenService.create(user.id).map { authToken =>
              val url = routes.ResetPasswordController.view(authToken.id).absoluteURL()

              mailerClient.send(Email(
                subject = Messages("email.reset.password.subject"),
                from = Messages("email.from"),
                to = Seq(email),
                bodyText = Some(web.view.auth.emails.txt.resetPassword(user, url).body),
                bodyHtml = Some(web.view.auth.emails.html.resetPassword(user, url).body)
              ))
              result
            }
          case None => Future.successful(result)
        }
        */
        Future.successful(result)
      }
    )
  }
}
