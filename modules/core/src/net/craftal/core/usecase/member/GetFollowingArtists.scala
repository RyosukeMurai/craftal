package net.craftal.core.usecase.member

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}

import scala.concurrent.Future

class GetFollowingArtists @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(memberId: Int): Future[List[Artist]] = this.repository.findArtistsByFollowerId(memberId)
}
