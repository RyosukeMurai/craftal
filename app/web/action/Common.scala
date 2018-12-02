package web.action

import play.api.mvc._
import web.model.common.Navigation

trait Common {
  def ActionWithNavigation[A](action: Action[A]): Action[A] = Action.async(action.parser) { request =>
    action(NavigationContext(Seq(), request))
  }
}


case class NavigationContext[A](navigationCollection: Seq[Navigation], request: Request[A])
  extends WrappedRequest(request)

