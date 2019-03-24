package net.craftal.core.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.artist.{Artist, ArtistRepository}

import scala.concurrent.Future

class GetArtistsOfGenre @Inject()(repository: ArtistRepository) extends Interactor {

  def execute(genreId: Int, keyword: Option[String]): Future[List[Artist]] =
    this.repository.findArtistsByGenreId(genreId, keyword)
}
