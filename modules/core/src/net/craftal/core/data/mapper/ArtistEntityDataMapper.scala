package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.artist.{Artist, ArtistPhoto}

object ArtistEntityDataMapper {
  def transform(userEntity: Tables.UserRow): Artist =
    Artist(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email,
      genreId = 1
    )

  def transform(userEntity: Tables.UserRow,
                photoCollection: List[Tables.ArtistPhotoRow]): Artist =
    Artist(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email,
      genreId = 1,
      photoCollection.map(photo => ArtistPhoto(
        photoId = photo.photoId,
        positionNo = photo.positionNo
      ))
    )

  def transformCollection(eventRows: List[(Tables.UserRow, Tables.ArtistPhotoRow)]): List[Artist] =
    eventRows
      .groupBy(_._1.id)
      .map(m => this.transform(m._2.head._1, m._2.map(_._2)))
      .toList

}
