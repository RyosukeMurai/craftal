package auth.service

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import javax.inject.Inject
import useCase.auth.{CreateAuthInfoByPassword, GetPasswordAuthInfo, GetPasswordAuthInfoByEmail}
import useCase.user.GetUserByEmail

import scala.concurrent.{ExecutionContext, Future}

class PasswordInfoService @Inject()(createAuthInfoByPassword: CreateAuthInfoByPassword,
                                    getPasswordAuthInfo: GetPasswordAuthInfo,
                                    getPasswordAuthInfoByEmail: GetPasswordAuthInfoByEmail,
                                    getUserByEmail: GetUserByEmail)
                                   (implicit ex: ExecutionContext) extends DelegableAuthInfoDAO[PasswordInfo] {

  override def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] =
    this.getPasswordAuthInfoByEmail.execute(loginInfo.providerKey).map(_.map({
      p => PasswordInfo(hasher = p.hasher, password = p.password, salt = Option(p.salt))
    }))

  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = {
    val salt = authInfo.salt match {
      case Some(x) => x
      case _ => throw new IllegalArgumentException("salt is required property to make auth info")
    }
    for {
      u <- this.getUserByEmail.execute(loginInfo.providerKey).map {
        case Some(u) => u
        case _ => throw new IllegalArgumentException("there is no user with the specified email address")
      }
      _ <- this.createAuthInfoByPassword.execute(u.id, authInfo.hasher, authInfo.password, salt)
      p <- this.getPasswordAuthInfo.execute(u.id)
    } yield authInfo //PasswordInfo(hasher = p.hasher, password = p.password, salt = Option(p.salt))
  }

  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    Future.successful(null)

  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = {
    println("pInfo-save")
    println(loginInfo)
    println(authInfo)
    Future.successful(null)
  }

  override def remove(loginInfo: LoginInfo): Future[Unit] =
    throw new NotImplementedError()
}
