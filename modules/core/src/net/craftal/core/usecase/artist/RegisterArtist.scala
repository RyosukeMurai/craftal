package net.craftal.core.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class RegisterArtist @Inject()(artistRepository: ArtistRepository)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, genreId: Int): Future[Artist] =
    this.artistRepository.createArtist(
      userId = userId,
      genreId = genreId
    )
}
