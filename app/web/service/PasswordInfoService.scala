package web.service

import application.auth.UserDAO
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import javax.inject.Inject
import useCase.account.{GetAccount, GetAccountByEmail, GetAccountByEmailAndPassword}

import scala.concurrent.{ExecutionContext, Future}

class PasswordInfoService @Inject()(getAccount: GetAccount,
                                    getAccountByEmail: GetAccountByEmail,
                                    getAccountByEmailAndPassword: GetAccountByEmailAndPassword,
                                    userDAO: UserDAO)(implicit ex: ExecutionContext) extends DelegableAuthInfoDAO[PasswordInfo] {
  override def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] = {
    println("pInfo-find")
    Future.successful(null)
  }

  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = {
    println("pInfo-add")
    println(loginInfo)
    println(authInfo)
    Future.successful(null)
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
    Future.successful(null)
}
