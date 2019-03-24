package net.craftal.web.port.deadbolt

import be.objectify.deadbolt.scala.DeadboltExecutionContextProvider

import scala.concurrent.ExecutionContext

class CustomDeadboltExecutionContextProvider extends DeadboltExecutionContextProvider {
  override def get(): ExecutionContext = scala.concurrent.ExecutionContext.global
}
