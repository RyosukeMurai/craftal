package net.craftal.identityaccess.domain.model.identity

import java.util.UUID

import net.craftal.common.domain.model.ValueObject
import org.joda.time.DateTime

case class IdentityToken(token: UUID,
                         userId: Int,
                         expiry: DateTime) extends ValueObject[IdentityToken] {
}
