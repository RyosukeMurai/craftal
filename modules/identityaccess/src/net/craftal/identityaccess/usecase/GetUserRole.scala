package net.craftal.identityaccess.usecase

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.role.{Role, RoleRepository}

import scala.concurrent.Future

class GetUserRole @Inject()(repository: RoleRepository) extends Interactor {
  def execute(userId: Int): Future[Role] = this.repository.findUserRole(userId)
}
