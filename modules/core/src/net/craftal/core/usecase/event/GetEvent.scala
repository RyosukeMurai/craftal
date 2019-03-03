package net.craftal.core.usecase.event

import javax.inject._
import net.craftal.core.domain.model.event.{Event, EventRepository}

import scala.concurrent.Future

@Singleton
class GetEvent @Inject()(repository: EventRepository) extends Interactor {

  def execute(id: Int): Future[Event] = this.repository.find(id)
}
