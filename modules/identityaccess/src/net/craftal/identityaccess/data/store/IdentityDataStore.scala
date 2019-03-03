package net.craftal.identityaccess.data.store

import java.sql.Timestamp
import java.util.UUID

import javax.inject.{Inject, Singleton}
import net.craftal.common.data.Tables
import net.craftal.identityaccess.data.mapper.{IdentityTokenEntityDataMapper, PasswordIdentityEntityDataMapper}
import net.craftal.identityaccess.domain.model.identity.{IdentityRepository, IdentityToken, PasswordIdentity}
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class IdentityDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends IdentityRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findPasswordIdentity(userId: Int): Future[PasswordIdentity] = {
    val query = for {
      u <- Tables.User
      a <- Tables.UserAuth
      p <- Tables.UserAuthPassword
      if u.id === userId
      if a.userId === u.id
      if p.userId === u.id
    } yield (u, a, p)

    dbConfig
      .db
      .run(
        query.to[List].result.head.map(PasswordIdentityEntityDataMapper.transform)
      )
  }

  override def findPasswordIdentityByEmail(email: String): Future[Option[PasswordIdentity]] = {
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
        query.to[List].result.headOption.map(_.map(PasswordIdentityEntityDataMapper.transform))
      )
  }

  override def findIdentityTokenDetail(token: UUID): Future[Option[IdentityToken]] = {
    dbConfig
      .db
      .run(
        Tables.UserAuthToken.filter(_.token === token.toString)
          .result.headOption.map(_.map(IdentityTokenEntityDataMapper.transform))
      )
  }

  override def createIdentityByPassword(userId: Int, hasher: String, hashedPassword: String): Future[Int] = {
    val command = for {
      _ <- Tables.UserAuth.map(a => (a.userId, a.isActivated)) += (userId, false)
      _ <- Tables.UserAuthPassword.map(a => (a.userId, a.hasher, a.password)) += (userId, hasher, hashedPassword)
    } yield userId
    dbConfig.db.run(command)
  }

  override def createIdentityToken(userId: Int, token: UUID, expiry: DateTime): Future[String] = {
    val command = for {
      _ <- Tables.UserAuthToken
        .map(a => (a.userId, a.token, a.expiredAt)) += (userId, token.toString, new Timestamp(expiry.getMillis))
    } yield token.toString
    dbConfig.db.run(command)
  }

  override def removeIdentityToken(token: UUID): Future[Boolean] = {
    Future.successful(true)
  }

  override def updateUserIdentity(userId: Int, isActivated: Boolean): Future[Boolean] = {
    val query = for {u <- Tables.UserAuth if u.userId === userId} yield u.isActivated
    val command = for {_ <- query.update(isActivated)} yield true

    dbConfig
      .db
      .run(
        command
      )
  }

  override def updatePassword(userId: Int, hasher: String, hashedPassword: String): Future[Boolean] = {
    val query = for {u <- Tables.UserAuthPassword if u.userId === userId} yield (u.hasher, u.password)
    val command = for {_ <- query.update(hasher, hashedPassword)} yield true
    dbConfig
      .db
      .run(
        command
      )
  }
}

