package data.store

import java.sql.Timestamp
import java.util.UUID

import data.Tables
import data.mapper.{AuthTokenEntityDataMapper, PasswordAuthInfoEntityDataMapper}
import domain.model.auth.{AuthRepository, AuthToken, PasswordAuthInfo}
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class AuthDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends AuthRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findPasswordAuthInfo(id: Int): Future[PasswordAuthInfo] = {
    val query = for {
      u <- Tables.User
      a <- Tables.UserAuth
      p <- Tables.UserAuthPassword
      if u.id === id
      if a.userId === u.id
      if p.userId === u.id
    } yield (u, a, p)

    dbConfig
      .db
      .run(
        query.to[List].result.head.map(PasswordAuthInfoEntityDataMapper.transform)
      )
  }

  override def findPasswordAuthInfoByEmail(email: String): Future[Option[PasswordAuthInfo]] = {
    val query = for {
      u <- Tables.User
      a <- Tables.UserAuth
      p <- Tables.UserAuthPassword
      if u.email === email
      if a.userId === u.id
      if p.userId === u.id
    } yield (u, a, p)

    dbConfig
      .db
      .run(
        query.to[List].result.headOption.map(_.map(PasswordAuthInfoEntityDataMapper.transform))
      )
  }

  override def createAuthByPassword(userId: Int, hasher: String, hashedPassword: String): Future[Int] = {
    val command = for {
      _ <- Tables.UserAuth.map(a => (a.userId, a.isActivated)) += (userId, false)
      _ <- Tables.UserAuthPassword.map(a => (a.userId, a.hasher, a.password)) += (userId, hasher, hashedPassword)
    } yield userId
    dbConfig.db.run(command)
  }

  override def createAuthToken(userId: Int, token: UUID, expiry: DateTime): Future[String] = {
    val command = for {
      _ <- Tables.UserAuthToken.map(a => (a.userId, a.token, a.expiredAt)) += (userId, token.toString, new Timestamp(expiry.getMillis))
    } yield token.toString
    dbConfig.db.run(command)
  }

  override def findAuthToken(token: UUID): Future[Option[AuthToken]] = {
    dbConfig
      .db
      .run(
        Tables.UserAuthToken.filter(_.token === token.toString).result.headOption.map(_.map(AuthTokenEntityDataMapper.transform))
      )
  }

  override def removeAuthToken(token: UUID): Future[Boolean] = {
    Future.successful(true)
  }

  override def updateUserAuth(userId: Int, isActivated: Boolean): Future[Boolean] = {
    val query = for {u <- Tables.UserAuth if u.userId === userId} yield u.isActivated
    val command = for {_ <- query.update(isActivated)} yield true

    dbConfig
      .db
      .run(
        command
      )
  }
}

