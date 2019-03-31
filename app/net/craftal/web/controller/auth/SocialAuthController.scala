package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.impl.providers._
import javax.inject.Inject
import net.craftal.web.port.silhouette.DefaultEnv
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import web.controller.auth.routes

import scala.concurrent.{ExecutionContext, Future}


class SocialAuthController @Inject()(
                                      components: ControllerComponents,
                                      silhouette: Silhouette[DefaultEnv],
                                      authInfoRepository: AuthInfoRepository,
                                      socialProviderRegistry: SocialProviderRegistry
                                    )(
                                      implicit
                                      ex: ExecutionContext
                                    ) extends AbstractController(components) with I18nSupport with Logger {

  /**
    * Authenticates a user against a social provider.
    *
    * @param provider The ID of the provider to authenticate against.
    * @return The result to display.
    */
  def authenticate(provider: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    /*
    (socialProviderRegistry.get[SocialProvider](provider) match {
      case Some(p: SocialProvider with CommonSocialProfileBuilder) =>
        p.authenticate().flatMap {
          case Left(result) => Future.successful(result)
          case Right(authInfo) => for {
            profile <- p.retrieveProfile(authInfo)
            user <- Future.successful(UserAuthInfo(0, null, null, Option(Role(1, "", "")), true)) //userService.save(profile)
            authInfo <- authInfoRepository.save(profile.loginInfo, authInfo)
            authenticator <- silhouette.env.authenticatorService.create(profile.loginInfo)
            value <- silhouette.env.authenticatorService.init(authenticator)
            result <- silhouette.env.authenticatorService.embed(value, Redirect(routes.ApplicationController.index()))
          } yield {
            silhouette.env.eventBus.publish(LoginEvent(user, request))
            result
          }
        }
      case _ => Future.failed(new ProviderException(s"Cannot authenticate with unexpected social provider $provider"))
    }).recover {
      case e: ProviderException =>
        logger.error("Unexpected provider error", e)
        Redirect(routes.SignInController.view()).flashing("error" -> Messages("could.not.authenticate"))
    }
    */
    Future.successful(Redirect(routes.SignInController.view()).flashing("error" -> Messages("could.not.authenticate")))
  }
}
