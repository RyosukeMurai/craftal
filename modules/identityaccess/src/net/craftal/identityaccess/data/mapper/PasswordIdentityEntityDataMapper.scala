package net.craftal.identityaccess.data.mapper

import net.craftal.common.data.Tables
import net.craftal.identityaccess.domain.model.identity.PasswordIdentity

object PasswordIdentityEntityDataMapper {
  def transform(authRow: (Tables.UserRow, Tables.UserIdentityRow, Tables.UserIdentityPasswordRow)): PasswordIdentity =
    PasswordIdentity(
      userId = authRow._1.id,
      hasher = authRow._3.hasher,
      password = authRow._3.password,
      activated = authRow._2.isActivated
    )
}
