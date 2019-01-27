package web.security

import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object WebAlternativeDynamicResourceHandler extends DynamicResourceHandler {
  override def isAllowed[A](name: String,
                            meta: Option[Any],
                            handler: DeadboltHandler,
                            request: AuthenticatedRequest[A]): Future[Boolean] = Future(false)

  override def checkPermission[A](permissionValue: String,
                                  meta: Option[Any],
                                  deadboltHandler: DeadboltHandler,
                                  request: AuthenticatedRequest[A]): Future[Boolean] = Future(false)
}