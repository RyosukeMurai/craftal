package net.craftal.web.controller.auth

import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.{LogoutEvent, Silhouette}
import controllers.AssetsFinder
import javax.inject.Inject
import net.craftal.web.port.silhouette.DefaultEnv
import org.webjars.play.WebJarsUtil
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import net.craftal.web.controller.auth.routes

import scala.concurrent.Future

class ApplicationController @Inject()(
                                       components: ControllerComponents,
                                       silhouette: Silhouette[DefaultEnv]
                                     )(
                                       implicit
                                       webJarsUtil: WebJarsUtil,
                                       assets: AssetsFinder
                                     ) extends AbstractController(components) with I18nSupport {

  def index: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    Future.successful(Ok(net.craftal.web.view.auth.html.home(Option(""))))
  }

  def signOut: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    val result = Redirect(routes.ApplicationController.index())
    silhouette.env.eventBus.publish(LogoutEvent(request.identity, request))
    silhouette.env.authenticatorService.discard(request.authenticator, result)
  }
}
