package net.craftal.web

import be.objectify.deadbolt.scala.DeadboltExecutionContextProvider
import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.filters.AuthorizedRoutes
import net.craftal.web.port.deadbolt.{CustomAuthorizedRoutes, CustomDeadboltExecutionContextProvider, CustomHandlerCache}
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}

class DeadboltModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = Seq(
    bind[HandlerCache].to[CustomHandlerCache],
    bind[DeadboltExecutionContextProvider].to[CustomDeadboltExecutionContextProvider],
    bind[AuthorizedRoutes].to[CustomAuthorizedRoutes]
  )
}
