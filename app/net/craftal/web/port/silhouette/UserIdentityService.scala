package net.craftal.web.port.silhouette

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import javax.inject.Inject
import net.craftal.identityaccess.api.AuthenticationService

import scala.concurrent.{ExecutionContext, Future}

class UserIdentityService @Inject()(authenticationService: AuthenticationService)
                                   (implicit ex: ExecutionContext) extends IdentityService[UserIdentity] {
  override def retrieve(loginInfo: LoginInfo): Future[Option[UserIdentity]] = {
    (for {
      u <- this.authenticationService.getUser(loginInfo.providerKey).map {
        case Some(x) => x
        case None => throw new IllegalArgumentException("there is no user with the specified email address")
      }
      p <- this.authenticationService.getPasswordIdentity(u.email)
      r <- this.authenticationService.getUserRole(u.id)
    } yield Some(new UserIdentityAdapter(u, p, r))).recover { case _ => None }
  }
}