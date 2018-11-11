package data.repository

import data.Tables
import data.mapper.UserEntityDataMapper
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

  override def createUser(name: String, email: String): Future[Int] = {
    println("create user")
    dbConfig
      .db
      .run(
        Tables.User.map(a => (a.name, a.email)) returning Tables.User.map(_.id) += (name, email)
      )
  }

}

