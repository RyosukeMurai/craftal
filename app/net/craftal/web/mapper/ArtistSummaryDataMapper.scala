package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.web.model.artist.ArtistSummary


object ArtistSummaryDataMapper {

  def transform(artistCollection: List[Artist], genreCollection: List[Genre]): ArtistSummary =
    ArtistSummary(artistCollection.groupBy(a => genreCollection.find(g => g.id == a.genreId).get))
}
