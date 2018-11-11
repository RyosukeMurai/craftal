package web.controller

import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.providers._
import controllers.AssetsFinder
import javax.inject.Inject
import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import useCase.account.{NotifyAlreadySignedUp, NotifySignUp, RegisterAccount}
import web.DefaultEnv
import web.model.assembler.EmailNotificationRequestAssembler
import web.model.form.SignUpForm
import web.service.UserIdentityService

import scala.concurrent.{ExecutionContext, Future}

class SignUpController @Inject()(components: ControllerComponents,
                                 silhouette: Silhouette[DefaultEnv],
                                 userService: UserIdentityService,
                                 registerAccount: RegisterAccount,
                                 emailAssembler: EmailNotificationRequestAssembler,
                                 notifyAlreadySignedUp: NotifyAlreadySignedUp,
                                 notifySignUp: NotifySignUp
                                )(implicit
                                  webJarsUtil: WebJarsUtil,
                                  assets: AssetsFinder,
                                  ex: ExecutionContext
                                ) extends AbstractController(components) with I18nSupport {

  def view: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(web.view.auth.html.signUp(SignUpForm.form)))
  }

  def submit: Action[AnyContent] = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    SignUpForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(web.view.auth.html.signUp(form))),
      data => {
        val result = Redirect(routes.SignUpController.view()).flashing("info" -> Messages("sign.up.email.sent", data.email))
        val loginInfo = LoginInfo(CredentialsProvider.ID, data.email)
        userService.retrieve(loginInfo).flatMap {
          case Some(user) =>
            this.notifyAlreadySignedUp.execute(this.emailAssembler.assembleForAlreadySignedUpEmail(
              data.email,
              user,
              routes.SignInController.view().absoluteURL()
            ))
            Future.successful(result)
          case None =>
            for {
              r <- this.registerAccount.execute(data.name, loginInfo, data.password)
              _ <- this.notifySignUp.execute(this.emailAssembler.assembleForSignUpEmail(
                r.user,
                routes.ActivateAccountController.activate(java.util.UUID.fromString(r.token)).absoluteURL()
              ))
            } yield {
              silhouette.env.eventBus.publish(SignUpEvent(r.user, request)) //TODO(RyosukeMurai): merge to notify sign up
              result
            }
            Future.successful(result)
        }
      }
    )
  }
}
