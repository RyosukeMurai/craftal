package data.store

import java.util.UUID

import data.Tables
import data.mapper.PasswordAuthInfoEntityDataMapper
import domain.model.auth.{AuthRepository, PasswordAuthInfo}
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
      a <- Tables.Account
      u <- Tables.User
      if a.userId === u.id
      if a.userId === id
    } yield (a, u)

    dbConfig
      .db
      .run(
        query.to[List].result.head.map(PasswordAuthInfoEntityDataMapper.transform)
      )
  }

  override def findPasswordAuthInfoByEmail(email: String): Future[Option[PasswordAuthInfo]] = {
    val query = for {
      a <- Tables.Account
      u <- Tables.User
      if a.userId === u.id
      if u.email === email
    } yield (a, u)

    dbConfig
      .db
      .run(
        query.to[List].result.headOption.map(_.map(PasswordAuthInfoEntityDataMapper.transform))
      )
  }

  override def createAuthByPassword(userId: Int, hasher: String, hashedPassword: String, salt: String): Future[Int] = {
    dbConfig
      .db
      .run(
        Tables.Account.map(a => (a.userId, a.password)) returning Tables.Account.map(_.userId) += (userId, hashedPassword)
      )
  }

  override def createAuthToken(token: UUID, userId: Int, expiry: DateTime): Future[String] = {
    /*
    dbConfig
      .db
      .run(
        Tables.Account.map(a => (a.userId, a.password)) returning Tables.Account.map(_.userId) += (userId, password)
      )
      */
    Future.successful("")
  }
}

