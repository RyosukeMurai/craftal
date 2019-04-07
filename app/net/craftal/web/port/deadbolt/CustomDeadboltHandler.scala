package net.craftal.web.port.deadbolt

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import com.mohiva.play.silhouette.api.Silhouette
import javax.inject.Inject
import net.craftal.web.port.silhouette.DefaultEnv
import play.api.mvc.Results._
import play.api.mvc.{Request, Result}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CustomDeadboltHandler @Inject()(silhouette: Silhouette[DefaultEnv]) extends DeadboltHandler {

  override def beforeAuthCheck[A](request: Request[A]): Future[Option[Result]] = Future.successful(None)

  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] = {
    if (request.subject.isDefined) {
      Future(request.subject)
    } else {
      // compose processing with com.mohiva.play.silhouette.api.RequestHandlerBuilder
      for {
        u <- silhouette.env.authenticatorService.retrieve(request).flatMap {
          case Some(x) if x.isValid => silhouette.env.identityService.retrieve(x.loginInfo)
          case Some(x) if !x.isValid => Future.successful(None)
          case None => Future.successful(None)
        }
      } yield u.map(new CustomSubjectAdapter(_))
    }
  }

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = {
    Future.successful(None)
  }

  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] =
    Future.successful(
      request.subject.map(_ => Redirect(net.craftal.web.controller.auth.routes.ApplicationController.index()))
        .getOrElse(Redirect(net.craftal.web.controller.auth.routes.SignInController.view()))
    )
}