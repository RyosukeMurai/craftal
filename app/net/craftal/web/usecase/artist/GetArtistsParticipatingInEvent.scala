package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}

import scala.concurrent.{ExecutionContext, Future}

class GetArtistsParticipatingInEvent @Inject()(repository: ArtistRepository)
                                              (implicit ex: ExecutionContext) extends Interactor {

  def execute(eventId: Int, keyword: Option[String]): Future[List[Artist]] =
    this.repository.findArtistsByEventId(eventId, keyword)
}
