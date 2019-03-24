package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.{Event, EventRepository}

import scala.concurrent.Future

@Singleton
class GetEvent @Inject()(repository: EventRepository) extends Interactor {

  def execute(eventId: Int): Future[Event] = this.repository.findEvent(eventId)
}
