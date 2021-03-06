package net.craftal.core.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.{Event, EventRepository}

import scala.concurrent.Future

class GetEvent @Inject()(repository: EventRepository) extends Interactor {

  def execute(eventId: Int): Future[Event] = this.repository.findEvent(eventId)
}
