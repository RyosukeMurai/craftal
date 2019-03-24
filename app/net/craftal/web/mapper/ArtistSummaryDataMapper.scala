package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.web.model.artist.ArtistSummary


object ArtistSummaryDataMapper {

  def transform(artistCollection: List[Artist], photoCollection: List[Photo]): ArtistSummary =
    ArtistSummary(artistCollection.groupBy(a => Genre(a.genreId, "", None)))
}
