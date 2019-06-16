package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.artist.ArtistDescriptor


object ArtistListDataMapper extends DataMapper {

  def transform(artists: List[Artist],
                photos: List[Photo],
                prefectures: List[Prefecture]): Seq[ArtistDescriptor] =
    artists.map { a =>
      ArtistDescriptor(
        a.id,
        a.name,
        a.email,
        prefectures.find(_.id == a.prefectureId).get.name,
        PhotoDescriptorDataMapper.transform(photos.find(_.id == a.iconPhotoId).get)
      )
    }
}
