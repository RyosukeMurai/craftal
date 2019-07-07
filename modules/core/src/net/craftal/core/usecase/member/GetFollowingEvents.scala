package net.craftal.core.usecase.member

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.{Event, EventRepository}

import scala.concurrent.Future

class GetFollowingEvents @Inject()(repository: EventRepository) extends Interactor {

  def execute(memberId: Int): Future[List[Event]] = this.repository.findEventsByFollowerId(memberId)
}
