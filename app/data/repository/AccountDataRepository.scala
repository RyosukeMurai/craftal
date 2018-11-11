package data.repository

import java.util.UUID

import data.Tables
import data.mapper.AccountEntityDataMapper
import domain.model.account.{Account, AccountRepository}
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class AccountDataRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends AccountRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def find(id: Int): Future[Account] = {
    val query = for {
      a <- Tables.Account
      u <- Tables.User
      if a.userId === u.id
      if a.userId === id
    } yield (a, u)

    dbConfig
      .db
      .run(
        query.to[List].result.head.map(AccountEntityDataMapper.transform)
      )
  }

  override def findByEmail(email: String): Future[Option[Account]] = {
    val query = for {
      a <- Tables.Account
      u <- Tables.User
      if a.userId === u.id
      if u.email === email
    } yield (a, u)

    dbConfig
      .db
      .run(
        query.to[List].result.headOption.map(_.map(AccountEntityDataMapper.transform))
      )
  }

  override def findByEmailAndPassword(email: String, password: String): Future[Option[Account]] = {
    val query = for {
      a <- Tables.Account
      u <- Tables.User
      if a.userId === u.id
      if u.email === email
      if a.password === password
    } yield (a, u)

    dbConfig
      .db
      .run(
        query.to[List].result.headOption.map(_.map(AccountEntityDataMapper.transform))
      )
  }

  override def createAccount(userId: Int, password: String): Future[Int] = {
    dbConfig
      .db
      .run(
        Tables.Account.map(a => (a.userId, a.password)) returning Tables.Account.map(_.userId) += (userId, password)
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

