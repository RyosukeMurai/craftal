package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.artist.{Artist, ArtistPhoto}

object ArtistEntityDataMapper {
  def transform(entity: (Tables.UserRow, Tables.ArtistRow, List[Tables.ArtistPhotoRow])): Artist =
    Artist(
      id = entity._1.id,
      name = entity._1.name,
      email = entity._1.email,
      genreId = entity._2.genreId,
      prefectureId = entity._2.prefectureId,
      entity._3.map(photo => ArtistPhoto(
        photoId = photo.photoId,
        positionNo = photo.positionNo
      ))
    )


  def transformCollection(entities: List[(Tables.UserRow, Tables.ArtistRow, Tables.ArtistPhotoRow)]): List[Artist] = {
    entities
      .groupBy(_._1.id)
      .map(m => this.transform(m._2.head._1, m._2.head._2, m._2.map(_._3)))
      .toList
  }

}
