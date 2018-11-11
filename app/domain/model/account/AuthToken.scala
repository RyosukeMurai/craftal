package domain.model.account

import java.util.UUID

import domain.model.shared.ValueObject
import org.joda.time.DateTime

class AuthToken(val token: UUID,
                val userId: Int,
                val expiry: DateTime) extends ValueObject[AuthToken] {
}
