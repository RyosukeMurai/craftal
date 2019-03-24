package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetEvent @Inject()(domainService: DomainService)
                        (implicit executionContext: ExecutionContext) extends Interactor {

  def execute(eventId: Int)
             (implicit request: Request[AnyContent], messages: Messages): Future[(Event, List[Artist])] =
    this.domainService.getEvent(eventId) zip this.domainService.getArtistsParticipatingInEvent(eventId, None)
}