package auth

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import javax.inject.Inject

import useCase.auth.{CreateAuthInfoByPassword, GetPasswordAuthInfo, GetPasswordAuthInfoByEmail, UpdatePassword}
import useCase.user.GetUserByEmail

import scala.concurrent.{ExecutionContext, Future}

class PasswordInfoService @Inject()(createAuthInfoByPassword: CreateAuthInfoByPassword,
                                    updatePassword: UpdatePassword,
                                    getPasswordAuthInfo: GetPasswordAuthInfo,
                                    getPasswordAuthInfoByEmail: GetPasswordAuthInfoByEmail,
                                    getUserByEmail: GetUserByEmail)
                                   (implicit ex: ExecutionContext) extends DelegableAuthInfoDAO[PasswordInfo] {

  override def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] =
    this.getPasswordAuthInfoByEmail.execute(loginInfo.providerKey).map(_.map({
      p => PasswordInfo(hasher = p.hasher, password = p.password)
    }))

  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = {
    for {
      u <- this.getUserByEmail.execute(loginInfo.providerKey).map {
        case Some(u) => u
        case _ => throw new IllegalArgumentException("there is no user with the specified email address")
      }
      _ <- this.createAuthInfoByPassword.execute(u.id, authInfo.hasher, authInfo.password)
      p <- this.getPasswordAuthInfo.execute(u.id)
    } yield PasswordInfo(hasher = p.hasher, password = p.password)
  }

  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    for {
      u <- this.getUserByEmail.execute(loginInfo.providerKey).map {
        case Some(u) => u
        case _ => throw new IllegalArgumentException("there is no user with the specified email address")
      }
      _ <- this.updatePassword.execute(u.id, authInfo.hasher, authInfo.password)
      p <- this.getPasswordAuthInfo.execute(u.id)
    } yield PasswordInfo(hasher = p.hasher, password = p.password)

  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    throw new NotImplementedError()

  override def remove(loginInfo: LoginInfo): Future[Unit] =
    throw new NotImplementedError()
}
