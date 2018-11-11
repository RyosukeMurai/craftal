package useCase.account.reponse

import web.model.auth.UserIdentity

case class AccountRegistrationResponse(user: UserIdentity, token: String) {

}
