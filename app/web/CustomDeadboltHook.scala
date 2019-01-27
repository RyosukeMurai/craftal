package web

import be.objectify.deadbolt.scala.DeadboltExecutionContextProvider
import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.filters.AuthorizedRoutes
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import web.security.{CustomDeadboltExecutionContextProvider, WebHandlerCache}

class CustomDeadboltHook extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = Seq(
    bind[HandlerCache].to[WebHandlerCache],
    bind[DeadboltExecutionContextProvider].to[CustomDeadboltExecutionContextProvider],
    bind[AuthorizedRoutes].to[WebAuthorizedRoutes]
  )
}
