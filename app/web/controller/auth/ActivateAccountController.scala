package web.controller.auth

import java.net.URLDecoder
import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import play.api.i18n.{I18nSupport, Messages}
import play.api.libs.mailer.MailerClient
import play.api.mvc._
import useCase.auth.{ActivateUser, ValidateAuthToken}
import web.DefaultEnv

import scala.concurrent.{ExecutionContext, Future}

class ActivateAccountController @Inject()(
                                           components: ControllerComponents,
                                           silhouette: Silhouette[DefaultEnv],
                                           validateAuthToken: ValidateAuthToken,
                                           activateUser: ActivateUser,
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

  def activate(token: UUID): Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    validateAuthToken.execute(token).flatMap {
      case true =>
        this.activateUser.execute(token).map {
          case true
          => Redirect(routes.SignInController.view()).flashing("success" -> Messages("account.activated"))
          case false
          => Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link"))
        }
      case false =>
        Future.successful(
          Redirect(routes.SignInController.view()).flashing("error" -> Messages("invalid.activation.link"))
        )
    }
  }
}
