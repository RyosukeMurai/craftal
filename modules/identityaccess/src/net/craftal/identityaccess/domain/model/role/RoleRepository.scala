package net.craftal.identityaccess.domain.model.role

import scala.concurrent.Future

trait RoleRepository {

  def findUserRole(userId: Int): Future[Role]

  def addUserRole(userId: Int, roleId: Int): Future[Int]

}
