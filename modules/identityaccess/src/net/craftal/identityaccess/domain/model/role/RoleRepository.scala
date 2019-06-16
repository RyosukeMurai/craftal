package net.craftal.identityaccess.domain.model.role

import net.craftal.identityaccess.domain.model.role.RoleCode.RoleCode

import scala.concurrent.Future

trait RoleRepository {

  def findUserRole(userId: Int): Future[Role]

  def findRole(roleCode: RoleCode): Future[Role]

  def addUserRole(userId: Int, roleId: Int): Future[Int]

  def addUserRole(userId: Int, roleCode: RoleCode): Future[Int]

}
