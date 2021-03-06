package net.craftal.web.port.silhouette

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import javax.inject.Inject
import net.craftal.identityaccess.api.AuthenticationService

import scala.concurrent.{ExecutionContext, Future}

class PasswordInfoService @Inject()(authentication: AuthenticationService)
                                   (implicit ex: ExecutionContext) extends DelegableAuthInfoDAO[PasswordInfo] {

  override def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] =
    for {
      u <- this.authentication.authenticate(loginInfo.providerKey)
      result <- this.authentication.getPasswordIdentity(u.email).map {
        p => Option(PasswordInfo(hasher = p.hasher, password = p.password))
      }
    } yield result

  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    for {
      _ <- this.authentication.register(loginInfo.providerKey, authInfo.hasher, authInfo.password)
    } yield PasswordInfo(hasher = authInfo.hasher, password = authInfo.password)

  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    for {
      p <- this.authentication.getPasswordIdentity(loginInfo.providerKey)
      _ <- this.authentication.changePassword(p.userId, authInfo.hasher, authInfo.password)
    } yield PasswordInfo(hasher = authInfo.hasher, password = authInfo.password)

  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    throw new NotImplementedError()

  override def remove(loginInfo: LoginInfo): Future[Unit] =
    throw new NotImplementedError()
}
