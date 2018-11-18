package auth.model.response

import java.util.UUID

case class AccountRegistrationResponse(email: String,
                                       name: Option[String],
                                       activated: Boolean,
                                       token: UUID) {

}
