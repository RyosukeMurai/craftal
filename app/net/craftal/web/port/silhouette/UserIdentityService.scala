package net.craftal.web.port.silhouette

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import javax.inject.Inject
import net.craftal.identityaccess.api.AuthenticationService

import scala.concurrent.{ExecutionContext, Future}

class UserIdentityService @Inject()(authenticationService: AuthenticationService)
                                   (implicit ex: ExecutionContext) extends IdentityService[UserIdentity] {
  override def retrieve(loginInfo: LoginInfo): Future[Option[UserIdentity]] = {
    for {
      u <- this.authenticationService.getUser(loginInfo.providerKey)
      p <- this.authenticationService.getPasswordIdentity(loginInfo.providerKey)
      _ <- u match {
        case Some(x) => this.authenticationService.getUserRole(x.id).map(Some(_))
        case _ => Future(None)
      }
    } yield {
      u match {
        case Some(us) => Some(
          UserIdentityAdapter(us, p)
        )
        case None => None
      }
    }
  }
}