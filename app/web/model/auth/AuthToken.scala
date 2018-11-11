package web.model.auth

import java.util.UUID

import org.joda.time.DateTime

/**
  * A token to authenticate a user against an endpoint for a short time period.
  *
  * @param id     The unique token ID.
  * @param userId The unique ID of the user the token is associated with.
  * @param expiry The date-time the token expires.
  */
case class AuthToken(id: UUID,
                     userId: Int,
                     expiry: DateTime)