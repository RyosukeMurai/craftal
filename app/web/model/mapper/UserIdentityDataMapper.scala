package web.model.mapper

import domain.model.account.Account
import web.model.auth.UserIdentity


object UserIdentityDataMapper {
  def transform(account: Account): UserIdentity =
    UserIdentity(
      account.userId,
      Option(account.email),
      Option(account.name),
      account.activated
    )
}
