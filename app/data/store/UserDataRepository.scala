package data.store

import java.util.UUID

import data.Tables
import data.mapper.{RoleEntityDataMapper, UserEntityDataMapper}
import domain.model.user.{User, UserRepository}
import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class UserDataRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends UserRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def find(id: Int): Future[User] =
    dbConfig.db
      .run(Tables.User.filter(_.id === id).result.head.map(UserEntityDataMapper.transform))

  override def findByEmail(email: String): Future[Option[User]] =
    dbConfig.db
      .run(Tables.User.filter(_.email === email).result.headOption.map(_.map(UserEntityDataMapper.transform)))

  override def findByToken(token: UUID): Future[Option[User]] = {
    val query = for {
      u <- Tables.User
      t <- Tables.UserAuthToken
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

