package web.mapper

import domain.model.artist._
import domain.model.photo.Photo
import web.model.artist._


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
