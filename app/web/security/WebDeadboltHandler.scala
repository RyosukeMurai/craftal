package web.security

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import play.api.mvc.Results._
import play.api.mvc.{Request, Result}
import security.WebDynamicResourceHandler

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class WebDeadboltHandler(dynamicResourceHandler: Option[DynamicResourceHandler] = None) extends DeadboltHandler {

  override def beforeAuthCheck[A](request: Request[A]): Future[Option[Result]] = Future(None)

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = {
    Future(dynamicResourceHandler.orElse(Some(new WebDynamicResourceHandler())))
  }

  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] =
    Future(None)

  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] =
    getSubject(request).map(_.map { _ => Forbidden }.getOrElse(Unauthorized))
}