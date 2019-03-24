package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.EventRepository

import scala.concurrent.Future

class CountNumberOfEvents @Inject()(repository: EventRepository) extends Interactor {

  def execute(): Future[Int] = this.repository.countNumberOfEvents
}
