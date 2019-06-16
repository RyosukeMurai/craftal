package net.craftal.identityaccess.data.store

import javax.inject.Inject
import net.craftal.common.data.Tables
import net.craftal.identityaccess.data.mapper.RoleEntityDataMapper
import net.craftal.identityaccess.domain.model.role.RoleCode.RoleCode
import net.craftal.identityaccess.domain.model.role.{Role, RoleRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


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

  override def findRole(roleCode: RoleCode): Future[Role] = {
    val query = for {
      r <- Tables.Role
      if r.code === roleCode.toString
    } yield r

    dbConfig
      .db
      .run(
        query.result.head.map(RoleEntityDataMapper.transform)
      )
  }

  override def addUserRole(userId: Int, roleId: Int): Future[Int] = {
    dbConfig
      .db
      .run(
        Tables.UserRole.map(r => (r.userId, r.roleId)) returning Tables.UserRole.map(_.id) += (userId, roleId.toByte)
      )
  }

  override def addUserRole(userId: Int, roleCode: RoleCode): Future[Int] = {
    val retrieveRole = Tables.Role.filter(r => r.code === roleCode.toString).result.head
    val insertUserRole = for {
      r <- retrieveRole
      id <- Tables.UserRole.map(r => (r.userId, r.roleId)) returning Tables.UserRole.map(_.id) += (userId, r.id)
    } yield id

    dbConfig
      .db
      .run(insertUserRole)
  }
}

