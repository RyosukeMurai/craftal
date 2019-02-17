package auth

import auth.model.UserAuthInfo
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import javax.inject.Inject
import useCase.auth.{GetPasswordAuthInfoByEmail, GetUserRole}
import useCase.user.GetUserByEmail

import scala.concurrent.{ExecutionContext, Future}

class UserIdentityService @Inject()(getUserByEmail: GetUserByEmail,
                                    getUserRole: GetUserRole,
                                    getPasswordAuthInfoByEmail: GetPasswordAuthInfoByEmail)
                                   (implicit ex: ExecutionContext) extends IdentityService[UserAuthInfo] {
  override def retrieve(loginInfo: LoginInfo): Future[Option[UserAuthInfo]] = {
    for {
      u <- this.getUserByEmail.execute(loginInfo.providerKey)
      p <- this.getPasswordAuthInfoByEmail.execute(loginInfo.providerKey)
      r <- u match {
        case Some(x) => this.getUserRole.execute(x.id).map(Some(_))
        case _ => Future(None)
      }
    } yield {
      u match {
        case Some(us) => Some(
          UserAuthInfo(us.id, Option(us.email), Option(us.name), r, p.exists(_.activated))
        )
        case None => None
      }
    }
  }
}