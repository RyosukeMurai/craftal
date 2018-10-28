package web.controller

import application.auth.DefaultEnv
import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.{LogoutEvent, Silhouette}
import controllers.AssetsFinder
import javax.inject.Inject
import org.webjars.play.WebJarsUtil
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.Future

/**
  * The basic application controller.
  *
  * @param components  The Play controller components.
  * @param silhouette  The Silhouette stack.
  * @param webJarsUtil The webjar util.
  * @param assets      The Play assets finder.
  */
class ApplicationController @Inject()(
                                       components: ControllerComponents,
                                       silhouette: Silhouette[DefaultEnv]
                                     )(
                                       implicit
                                       webJarsUtil: WebJarsUtil,
                                       assets: AssetsFinder
                                     ) extends AbstractController(components) with I18nSupport {

  /**
    * Handles the index action.
    *
    * @return The result to display.
    */
  def index: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    Future.successful(Ok(web.view.auth.html.home(request.identity)))
  }

  /**
    * Handles the Sign Out action.
    *
    * @return The result to display.
    */
  def signOut: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    val result = Redirect(routes.ApplicationController.index())
    silhouette.env.eventBus.publish(LogoutEvent(request.identity, request))
    silhouette.env.authenticatorService.discard(request.authenticator, result)
  }
}
