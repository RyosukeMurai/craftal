package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService

import scala.concurrent.{ExecutionContext, Future}

class CountNumberOfEvents @Inject()(domainService: DomainService)
                                   (implicit executionContext: ExecutionContext)extends Interactor {

  def execute(): Future[Int] = this.domainService.countNumberOfEvents
}
