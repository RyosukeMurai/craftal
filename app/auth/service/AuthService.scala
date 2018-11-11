package auth.service


import javax.inject.Inject
import useCase.auth.{GetPasswordAuthInfo, GetPasswordAuthInfoByEmail}

import scala.concurrent.ExecutionContext

//"auth" is an abbreviation containing authorization and authentication
class AuthService @Inject()(getAccount: GetPasswordAuthInfo,
                            getAccountByEmail: GetPasswordAuthInfoByEmail)(implicit ex: ExecutionContext) {

  //def validate(id: UUID): Future[Option[AuthToken]] =
}
