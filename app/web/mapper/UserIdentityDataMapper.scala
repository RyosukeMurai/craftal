package web.mapper

import auth.model.UserAuthInfo
import domain.model.user.User


object UserIdentityDataMapper {
  def transform(user: User): UserAuthInfo =
    UserAuthInfo(
      user.id,
      Option(user.email),
      Option(user.name),
      user.activated
    )
}
