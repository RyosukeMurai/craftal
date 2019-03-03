package net.craftal.identityaccess.data.store

import javax.inject.Inject
import net.craftal.common.data.Tables
import net.craftal.identityaccess.data.mapper.RoleEntityDataMapper
import net.craftal.identityaccess.domain.model.role.{Role, RoleRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class RoleDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends RoleRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findUserRole(userId: Int): Future[Role] = {
    val query = for {
      u <- Tables.User
      ur <- Tables.UserRole
      r <- Tables.Role
      if u.id === ur.userId
    } yield r

    dbConfig
      .db
      .run(
        query.result.head.map(RoleEntityDataMapper.transform)
      )
  }
}

