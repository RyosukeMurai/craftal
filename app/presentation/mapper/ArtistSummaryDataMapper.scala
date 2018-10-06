package presentation.mapper

import domain.artist._
import domain.photo.Photo
import presentation.model.artist._


object ArtistSummaryDataMapper {

  def transform(artistCollection: List[Artist], photoCollection: List[Photo]): ArtistSummary =
    ArtistSummary(artistCollection
      .groupBy(_.getGenre)
      .map(gr =>
        ArtistGenreSummary(
          gr._1,
          gr._2.map(ArtistGenreSummaryContent(_, photoCollection))
        )
      )
      .toList
    )
}
