package web.security

import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.{DeadboltHandler, HandlerKey}
import javax.inject.Singleton

@Singleton
class WebHandlerCache extends HandlerCache {
  val defaultHandler: DeadboltHandler = new WebDeadboltHandler

  val handlers: Map[Any, DeadboltHandler] = Map(
    HandlerKeys.defaultHandler -> defaultHandler,
    HandlerKeys.altHandler -> new WebDeadboltHandler(Some(WebAlternativeDynamicResourceHandler)),
    HandlerKeys.userlessHandler -> new WebUserlessDeadboltHandler
  )

  override def apply(): DeadboltHandler = defaultHandler

  override def apply(handlerKey: HandlerKey): DeadboltHandler = handlers(handlerKey)
}
