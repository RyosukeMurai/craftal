package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.artist.{ArtistDescriptor, ArtistSummary}


object ArtistSummaryDataMapper extends DataMapper {

  def transform(artists: List[Artist],
                genres: List[Genre],
                photos: List[Photo],
                prefectures: List[Prefecture]): ArtistSummary =
    ArtistSummary(
      artists
        .groupBy(a => genres.find(g => g.id == a.genreId).get)
        .map { case (k, v) => k -> v.map(a => ArtistDescriptor(
          a.id,
          a.name,
          a.email,
          prefectures.find(_.id == a.prefectureId).get.name,
          PhotoDescriptorDataMapper.transform(photos.find(_.id == a.iconPhotoId).get)))
        }
    )
}
