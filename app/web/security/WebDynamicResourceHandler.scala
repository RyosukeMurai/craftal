package security

import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}

import scala.collection.immutable.Map
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class WebDynamicResourceHandler extends DynamicResourceHandler {
  override def isAllowed[A](name: String, meta: Option[Any], handler: DeadboltHandler, request: AuthenticatedRequest[A]): Future[Boolean] = {
    WebDynamicResourceHandler.handlers(name).isAllowed(name,
      meta,
      handler,
      request)
  }

  override def checkPermission[A](permissionValue: String, meta: Option[Any] = None, deadboltHandler: DeadboltHandler, request: AuthenticatedRequest[A]): Future[Boolean] = Future(false)
}

object WebDynamicResourceHandler {
  val handlers: Map[String, DynamicResourceHandler] =
    Map(
      "pureLuck" -> new DynamicResourceHandler() {
        override def isAllowed[A](name: String, meta: Option[Any], deadboltHandler: DeadboltHandler, request: AuthenticatedRequest[A]): Future[Boolean] =
          Future(System.currentTimeMillis() % 2 == 0)

        override def checkPermission[A](permissionValue: String, meta: Option[Any] = None, deadboltHandler: DeadboltHandler, request: AuthenticatedRequest[A]): Future[Boolean] = Future(false)
      }
    )
}