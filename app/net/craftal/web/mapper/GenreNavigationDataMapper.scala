package net.craftal.web.mapper

import net.craftal.core.domain.model.genre.Genre
import net.craftal.web.model.common.Navigation
import play.api.i18n.Messages


object GenreNavigationDataMapper extends DataMapper {

  def transform(genreCollection: List[Genre])(implicit messages: Messages): Seq[Navigation] =
    genreCollection.map(g => Navigation(g.name, net.craftal.web.controller.artist.routes.ListArtistByGenreController.view(g.id)))

}
