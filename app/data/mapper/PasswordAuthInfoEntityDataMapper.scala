package data.mapper

import data.Tables
import domain.model.auth.PasswordAuthInfo

object PasswordAuthInfoEntityDataMapper {
  def transform(accountRow: (Tables.AccountRow, Tables.UserRow)): PasswordAuthInfo =
    new PasswordAuthInfo(
      userId = accountRow._1.userId,
      hasher = accountRow._2.name,
      password = accountRow._2.email,
      salt = accountRow._2.email,
      activated = accountRow._1.isActivated
    )
}
