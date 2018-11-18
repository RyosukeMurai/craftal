package auth

import auth.model.UserAuthInfo
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import javax.inject.Inject
import useCase.auth.GetPasswordAuthInfoByEmail
import useCase.user.GetUserByEmail

import scala.concurrent.{ExecutionContext, Future}

class UserIdentityService @Inject()(getUserByEmail: GetUserByEmail,
                                    getPasswordAuthInfoByEmail: GetPasswordAuthInfoByEmail)
                                   (implicit ex: ExecutionContext) extends IdentityService[UserAuthInfo] {
  override def retrieve(loginInfo: LoginInfo): Future[Option[UserAuthInfo]] =
    for {
      uf <- this.getUserByEmail.execute(loginInfo.providerKey)
      pf <- this.getPasswordAuthInfoByEmail.execute(loginInfo.providerKey)
    } yield {
      //TODO(RyosukeMurai): remove nested for
      for {
        u <- uf
        p <- pf
      } yield UserAuthInfo(u.id, Option(u.email), Option(u.name), p.activated)
    }
}