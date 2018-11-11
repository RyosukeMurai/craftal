package auth.service

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import javax.inject.Inject
import useCase.auth.{GetPasswordAuthInfo, GetPasswordAuthInfoByEmail}
import web.model.auth.UserIdentity

import scala.concurrent.{ExecutionContext, Future}

class UserIdentityService @Inject()(getAccount: GetPasswordAuthInfo,
                                    getAccountByEmail: GetPasswordAuthInfoByEmail)
                                   (implicit ex: ExecutionContext) extends IdentityService[UserIdentity] {
  override def retrieve(loginInfo: LoginInfo): Future[Option[UserIdentity]] =
    throw new NotImplementedError()
}
