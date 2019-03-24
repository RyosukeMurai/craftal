package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}

import scala.concurrent.Future

class GetArtistsParticipatingInEvent @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(eventId: Int, keyword: Option[String]): Future[List[Artist]] =
    this.repository.findArtistsByEventId(eventId, keyword)
}
