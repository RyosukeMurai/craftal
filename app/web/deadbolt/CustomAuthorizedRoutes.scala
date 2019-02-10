package web.deadbolt

import be.objectify.deadbolt.scala.allOfGroup
import be.objectify.deadbolt.scala.filters.{AuthorizedRoute, AuthorizedRoutes, FilterConstraints, _}
import javax.inject.Inject

class CustomAuthorizedRoutes @Inject()(filterConstraints: FilterConstraints) extends AuthorizedRoutes {

  override val routes: Seq[AuthorizedRoute] =
    Seq(
      AuthorizedRoute(Get, "/events", filterConstraints.subjectPresent, handler = None),
      AuthorizedRoute(Get, "/events/$id<[^/]+>", filterConstraints.restrict(allOfGroup("someRole")), handler = None)
    )
}
