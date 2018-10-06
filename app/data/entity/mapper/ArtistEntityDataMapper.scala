package data.entity.mapper

import data.entity.Tables
import domain.artist.Artist
import domain.genre.Genre

object ArtistEntityDataMapper {
  def transform(userEntity: Tables.UserRow): Artist =
    new Artist(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email,
      genre = Genre("木工", None)
    )

  def transformCollection(userRows: List[Tables.UserRow]): List[Artist] =
    userRows
      .map(m => this.transform(m))
      .toList
}
