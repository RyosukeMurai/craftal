package net.craftal.web.port.deadbolt

import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.{DeadboltHandler, HandlerKey}
import javax.inject.{Inject, Singleton}

class CustomHandlerCache @Inject()(val defaultHandler: CustomDeadboltHandler) extends HandlerCache {

  override def apply(): DeadboltHandler = this.defaultHandler

  override def apply(handlerKey: HandlerKey): DeadboltHandler = this.defaultHandler

}
