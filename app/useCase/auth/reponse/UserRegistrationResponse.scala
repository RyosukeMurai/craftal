package useCase.auth.reponse

import java.util.UUID

import auth.model.UserAuthInfo

case class UserRegistrationResponse(user: UserAuthInfo, token: UUID) {

}
