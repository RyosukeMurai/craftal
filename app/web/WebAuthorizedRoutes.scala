package web

import javax.inject.Inject

import be.objectify.deadbolt.scala.allOfGroup
import be.objectify.deadbolt.scala.filters.{AuthorizedRoute, AuthorizedRoutes, FilterConstraints}
import be.objectify.deadbolt.scala.filters._

class WebAuthorizedRoutes @Inject()(filterConstraints: FilterConstraints) extends AuthorizedRoutes {

  override val routes: Seq[AuthorizedRoute] =
    Seq(
      AuthorizedRoute(Get, "/events", filterConstraints.subjectPresent, handler = None),
      AuthorizedRoute(Get, "/events/$id<[^/]+>", filterConstraints.restrict(allOfGroup("someRole")), handler = None)
    )
}
