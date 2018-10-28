package data.mapper

import domain.model.user.User
import data.Tables

object UserEntityDataMapper {
  def transform(userEntity: Tables.UserRow): User =
    new User(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email
    )
}
