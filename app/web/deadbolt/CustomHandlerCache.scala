package web.deadbolt

import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.{DeadboltHandler, HandlerKey}
import javax.inject.{Inject, Singleton}

@Singleton
class CustomHandlerCache @Inject()(defaultHandler: CustomDeadboltHandler) extends HandlerCache {

  val handlers: Map[Any, DeadboltHandler] = Map(HandlerKeys.defaultHandler -> defaultHandler)

  override def apply(): DeadboltHandler = defaultHandler

  override def apply(handlerKey: HandlerKey): DeadboltHandler = handlers(handlerKey)
}
