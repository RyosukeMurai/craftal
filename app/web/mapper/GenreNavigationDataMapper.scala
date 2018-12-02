package web.mapper

import domain.model.genre.Genre
import play.api.i18n.Messages
import web.model.common.Navigation


object GenreNavigationDataMapper {

  def transform(genreCollection: List[Genre])(implicit messages: Messages): Seq[Navigation] =
    genreCollection.map(g => Navigation(g.name, web.controller.artist.routes.ListArtistByGenreController.view(g.id)))

}
