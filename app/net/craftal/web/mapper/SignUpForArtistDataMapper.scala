package net.craftal.web.mapper

import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.auth.SignUpForArtist


object SignUpForArtistDataMapper extends DataMapper {
  def transform(prefectures: List[Prefecture], genres: List[Genre]): SignUpForArtist =
    SignUpForArtist(
      prefectures.map(p => (p.id.toString, p.name)),
      genres.map(g => (g.id.toString, g.name))
    )
}
