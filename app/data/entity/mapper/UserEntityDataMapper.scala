package data.entity.mapper

import data.entity.Tables
import domain.user.User

object UserEntityDataMapper {
  def transform(userEntity: Tables.UserRow): User =
    new User(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email
    )
}
