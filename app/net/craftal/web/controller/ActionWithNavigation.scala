package net.craftal.web.controller

import javax.inject.Inject
import net.craftal.web.model.common.Navigation
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class ActionWithNavigation @Inject()(val parser: BodyParsers.Default,
                                     val commonDataProvider: CommonDataProvider)
                                    (implicit val executionContext: ExecutionContext)
  extends ActionBuilder[NavigationContext, AnyContent] with ActionTransformer[Request, NavigationContext] {

  override protected def transform[A](request: Request[A]): Future[NavigationContext[A]] = Future.successful {
    NavigationContext[A](this.commonDataProvider.navigationCollectionForPublicAccess, request)
  }
}

case class NavigationContext[A](navigationCollection: Seq[Navigation], request: Request[A])
  extends WrappedRequest[A](request)

import play.api.mvc._

case class Logging[A](action: Action[A]) extends Action[A] {

  def apply(request: Request[A]): Future[Result] = {
    action(request)
  }

  override def parser: BodyParser[A] = action.parser

  override def executionContext: ExecutionContext = action.executionContext
}