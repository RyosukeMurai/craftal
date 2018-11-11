package web.model.mapper

import domain.model.user.User
import web.model.auth.UserIdentity


object UserIdentityDataMapper {
  def transform(user: User): UserIdentity =
    UserIdentity(
      user.id,
      Option(user.email),
      Option(user.name),
      user.activated
    )
}
