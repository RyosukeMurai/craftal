package net.craftal.core.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}

import scala.concurrent.Future

@Singleton
class GetArtist @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(artistId: Int): Future[Artist] = this.repository.findArtist(artistId)
}
