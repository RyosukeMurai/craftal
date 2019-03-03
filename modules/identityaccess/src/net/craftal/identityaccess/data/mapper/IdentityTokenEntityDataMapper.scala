package net.craftal.identityaccess.data.mapper

import java.util.UUID

import net.craftal.common.data.Tables
import net.craftal.identityaccess.domain.model.identity.IdentityToken
import org.joda.time.DateTime

object IdentityTokenEntityDataMapper {
  def transform(tokenRow: Tables.UserAuthTokenRow): IdentityToken =
    IdentityToken(
      token = UUID.fromString(tokenRow.token),
      userId = tokenRow.userId,
      expiry = new DateTime(tokenRow.expiredAt.getTime)
    )
}
