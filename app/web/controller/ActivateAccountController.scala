package web.controller

import java.net.URLDecoder
import java.util.UUID

import auth.UserIdentityService
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import javax.inject.Inject
import play.api.i18n.{I18nSupport, Messages}
import play.api.libs.mailer.MailerClient
import play.api.mvc._
import web.DefaultEnv

import scala.concurrent.{ExecutionContext, Future}

class ActivateAccountController @Inject()(
                                           components: ControllerComponents,
                                           silhouette: Silhouette[DefaultEnv],
                                           userService: UserIdentityService,
                                           mailerClient: MailerClient
                                         )(
                                           implicit
                                           ex: ExecutionContext
                                         ) extends AbstractController(components) with I18nSupport {

  /**
    * Sends an account activation email to the user with the given email.
    *
    * @param email The email address of the user to send the activation mail to.
    * @return The result to display.
    */
  def send(email: String): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    val decodedEmail = URLDecoder.decode(email, "UTF-8")
    val loginInfo = LoginInfo(CredentialsProvider.ID, decodedEmail)
    val result = Redirect(routes.SignInController.view()).flashing("info" -> Messages("activation.email.sent", decodedEmail))
    /*
    userService.retrieve(loginInfo).flatMap {
      case Some(user) if !user.activated =>
        authTokenService.create(user.id).map { authToken =>
          val url = routes.ActivateAccountController.activate(authToken.id).absoluteURL()

          mailerClient.send(Email(
            subject = Messages("email.activate.account.subject"),
            from = Messages("email.from"),
            to = Seq(decodedEmail),
            bodyText = Some(activateAccount(user, url).body),
            bodyHtml = Some(activateAccount(user, url).body)
          ))
          result
        }
      case None => Future.successful(result)
    }
    */
    Future.successful(result)
  }

  /**
    * Activates an account.
    *
    * @param token The token to identify a user.
    * @return The result to display.
    */
  def activate(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    /*
    authTokenService.validate(token).flatMap {
      case Some(authToken) => userService.retrieve(authToken.userId).flatMap {
        case Some(user) if user.loginInfo.providerID == CredentialsProvider.ID =>
          userService.save(user.copy(activated = true)).map { _ =>
            Redirect(routes.SignInController.view()).flashing("success" -> Messages("account.activated"))
          }
        case _ => Future.successful(Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link")))
      }
      case None => Future.successful(Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link")))
    }
    */
    Future.successful(Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link")))
  }
}
