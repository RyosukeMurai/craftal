package web.deadbolt

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import com.mohiva.play.silhouette.api.Silhouette
import com.mohiva.play.silhouette.api.actions.{DefaultSecuredErrorHandler, DefaultSecuredRequestHandler, SecuredRequest}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import javax.inject.Inject
import play.api.i18n.MessagesApi
import play.api.mvc.Results._
import play.api.mvc.{AnyContent, Request, Result}
import useCase.auth.GetUserRole
import web.model.auth.AuthSubject
import web.silhouette.DefaultEnv

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CustomDeadboltHandler @Inject()(getUserRole: GetUserRole,
                                      silhouette: Silhouette[DefaultEnv]) extends DeadboltHandler {

  override def beforeAuthCheck[A](request: Request[A]): Future[Option[Result]] = Future.successful(None)

  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] =
    if (request.subject.isDefined) {
      Future.successful(request.subject)
    } else {
      // compose processing with com.mohiva.play.silhouette.api.RequestHandlerBuilder
      silhouette.env.authenticatorService.retrieve(request).flatMap {
        // A valid authenticator was found so we retrieve also the identity
        case Some(a) if a.isValid =>
          silhouette.env.identityService.retrieve(a.loginInfo).map(i => i)
        // An invalid authenticator was found so we needn't retrieve the identity
        case Some(a) if !a.isValid => Future.successful(None)
        // No authenticator was found so we try to authenticate with a request provider
        case None => handleRequestProviderAuthentication(request).flatMap {
          // Authentication was successful, so we retrieve the identity and create a new authenticator for it
          case Some(loginInfo) => silhouette.env.identityService.retrieve(loginInfo).flatMap { (i: Option[User]) =>
            silhouette.env.authenticatorService.create(loginInfo)(request).map((a: CookieAuthenticator) => i)
          }
          // No identity and no authenticator was found
          case None => Future.successful(None)
        }
      }
    }


  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = Future.successful(None)

  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] =
    Future.successful(
      request.subject.map(_ => Redirect(web.controller.auth.routes.ApplicationController.index()))
        .getOrElse(Redirect(web.controller.auth.routes.SignInController.view()))
    )
}