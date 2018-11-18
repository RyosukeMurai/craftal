package data.mapper

import data.Tables
import domain.model.auth.PasswordAuthInfo

object PasswordAuthInfoEntityDataMapper {
  def transform(authRow: (Tables.UserRow, Tables.UserAuthRow, Tables.UserAuthPasswordRow)): PasswordAuthInfo =
    PasswordAuthInfo(
      userId = authRow._1.id,
      hasher = authRow._3.hasher,
      password = authRow._3.password,
      activated = authRow._2.isActivated
    )
}
