package domain.model.auth

import java.util.UUID

import domain.model.shared.ValueObject
import org.joda.time.DateTime

case class AuthToken(token: UUID,
                     userId: Int,
                     expiry: DateTime) extends ValueObject[AuthToken] {
}
