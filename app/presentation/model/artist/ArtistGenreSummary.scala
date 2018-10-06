package presentation.model.artist

import domain.genre.Genre

case class ArtistGenreSummary(genre: Genre,
                              private val artists: List[ArtistGenreSummaryContent]) {

  def getArtists: List[ArtistGenreSummaryContent] = this.artists.toList
}
