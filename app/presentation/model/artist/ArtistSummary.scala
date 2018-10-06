package presentation.model.artist

case class ArtistSummary(private val genres: List[ArtistGenreSummary]) {

  def getGenres: List[ArtistGenreSummary] = this.genres.toList
}
