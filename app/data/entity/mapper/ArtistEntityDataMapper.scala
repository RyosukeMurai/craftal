package data.entity.mapper

import data.entity.Tables
import domain.artist.{Artist, ArtistPhoto}
import domain.genre.Genre

object ArtistEntityDataMapper {
  def transform(userEntity: Tables.UserRow): Artist =
    new Artist(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email,
      genre = Genre("木工", None)
    )

  def transform(userEntity: Tables.UserRow,
                photoCollection: List[Tables.ArtistPhotoRow]): Artist =
    new Artist(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email,
      genre = Genre("木工", None),
      photoCollection.map(photo => ArtistPhoto(
        artistId = photo.artistId,
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
