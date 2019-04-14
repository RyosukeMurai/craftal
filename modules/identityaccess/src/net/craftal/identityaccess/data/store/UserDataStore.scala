package net.craftal.identityaccess.data.store

import java.util.UUID

import javax.inject.{Inject, Singleton}
import net.craftal.common.data.Tables
import net.craftal.identityaccess.data.mapper.UserEntityDataMapper
import net.craftal.identityaccess.domain.model.user.{User, UserRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class UserDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends UserRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findUser(userId: Int): Future[User] =
    dbConfig.db
      .run(Tables.User.filter(_.id === userId).result.head.map(UserEntityDataMapper.transform))

  override def findUserByEmail(email: String): Future[Option[User]] =
    dbConfig.db
      .run(Tables.User.filter(_.email === email).result.headOption.map(_.map(UserEntityDataMapper.transform)))

  override def findUserByToken(token: UUID): Future[Option[User]] = {
    val query = for {
      u <- Tables.User
      t <- Tables.UserIdentityToken
      if t.token === token.toString
      if t.userId === u.id
    } yield u

    dbConfig
      .db
      .run(
        query.result.headOption.map(_.map(UserEntityDataMapper.transform))
      )
  }

  override def createUser(name: String, email: String): Future[Int] = {
    dbConfig
      .db
      .run(
        Tables.User.map(a => (a.name, a.email)) returning Tables.User.map(_.id) += (name, email)
      )
  }

}

