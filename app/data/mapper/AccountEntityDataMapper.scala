package data.mapper

import data.Tables
import domain.model.account.Account

object AccountEntityDataMapper {
  def transform(accountRow: (Tables.AccountRow, Tables.UserRow)): Account =
    new Account(
      userId = accountRow._1.userId,
      name = accountRow._2.name,
      email = accountRow._2.email,
      activated = accountRow._1.isActivated
    )
}
