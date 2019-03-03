package net.craftal.core.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}

import scala.concurrent.Future

@Singleton
class GetArtists @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(keyword: Option[String]): Future[List[Artist]] =
    this.repository.findArtistsByKeyword(keyword)
}
