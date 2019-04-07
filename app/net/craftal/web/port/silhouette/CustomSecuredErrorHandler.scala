package net.craftal.web.port.silhouette

import com.mohiva.play.silhouette.api.actions.SecuredErrorHandler
import javax.inject.Inject
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.Results._
import play.api.mvc.{RequestHeader, Result}

import scala.concurrent.Future

/**
  * Custom secured error silhouette.
  *
  * @param messagesApi The Play messages API.
  */
class CustomSecuredErrorHandler @Inject()(val messagesApi: MessagesApi) extends SecuredErrorHandler with I18nSupport {

  /**
    * Called when a user is not authenticated.
    *
    * As defined by RFC 2616, the status code of the response should be 401 Unauthorized.
    *
    * @param request The request header.
    * @return The result to send to the client.
    */
  override def onNotAuthenticated(implicit request: RequestHeader): Future[Result] = {
    Future.successful(Redirect(net.craftal.web.controller.auth.routes.SignInController.view()))
  }

  /**
    * Called when a user is authenticated but not authorized.
    *
    * As defined by RFC 2616, the status code of the response should be 403 Forbidden.
    *
    * @param request The request header.
    * @return The result to send to the client.
    */
  override def onNotAuthorized(implicit request: RequestHeader): Future[Result] = {
    Future.successful(Redirect(net.craftal.web.controller.auth.routes.SignInController.view()).flashing("error" -> Messages("access.denied")))
  }
}
