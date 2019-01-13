package web.action

import javax.inject.Inject

import play.api.mvc._
import web.model.common.Navigation
import web.service.CommonDataProvider

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
