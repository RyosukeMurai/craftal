package web.deadbolt

import be.objectify.deadbolt.scala.allOfGroup
import be.objectify.deadbolt.scala.filters.{AuthorizedRoute, AuthorizedRoutes, FilterConstraints, _}
import javax.inject.Inject

class CustomAuthorizedRoutes @Inject()(filterConstraints: FilterConstraints) extends AuthorizedRoutes {

  override val routes: Seq[AuthorizedRoute] =
    Seq(
      AuthorizedRoute(Get, "/manage/events", filterConstraints.restrict(allOfGroup("administrator")), handler = None),
      AuthorizedRoute(Get, "/manage/events/$id<[^/]+>", filterConstraints.restrict(allOfGroup("administrator")), handler = None)
    )
}
