package data.mapper

import java.util.UUID

import data.Tables
import domain.model.auth.AuthToken
import org.joda.time.DateTime

object AuthTokenEntityDataMapper {
  def transform(tokenRow: Tables.UserAuthTokenRow): AuthToken =
    AuthToken(
      token = UUID.fromString(tokenRow.token),
      userId = tokenRow.userId,
      expiry = new DateTime(tokenRow.expiredAt.getTime)
    )
}
