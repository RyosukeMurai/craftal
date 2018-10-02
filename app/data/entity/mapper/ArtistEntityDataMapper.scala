package data.entity.mapper

import data.entity.Tables
import domain.artist.Artist

object ArtistEntityDataMapper {
  def transform(userEntity: Tables.UserRow): Artist =
    new Artist(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email
    )
}
