package net.craftal.core.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.{Event, EventRepository}

import scala.concurrent.Future

class GetEventsForArtistParticipating @Inject()(repository: EventRepository) extends Interactor {

  def execute(artistId: Int): Future[List[Event]] = this.repository.findEventsByArtistId(artistId)
}
