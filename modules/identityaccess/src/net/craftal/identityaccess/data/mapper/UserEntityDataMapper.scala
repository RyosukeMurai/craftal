package net.craftal.identityaccess.data.mapper

import net.craftal.common.data.Tables
import net.craftal.identityaccess.domain.model.user.User

object UserEntityDataMapper {
  def transform(userEntity: Tables.UserRow): User =
    new User(
      id = userEntity.id,
      name = userEntity.name,
      email = userEntity.email
    )
}
