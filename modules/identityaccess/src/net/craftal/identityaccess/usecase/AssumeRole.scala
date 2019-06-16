package net.craftal.identityaccess.usecase

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.role.RoleCode.RoleCode
import net.craftal.identityaccess.domain.model.role.{Role, RoleRepository}
import net.craftal.identityaccess.domain.model.user.UserRepository

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class AssumeRole @Inject()(userRepository: UserRepository,
                           roleRepository: RoleRepository,
                           identityRepository: IdentityRepository)
                          (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, role: Role): Future[Boolean] = {
    for {
      _ <- this.roleRepository.addUserRole(userId, role.id)
    } yield true
  }

  def execute(userId: Int, roleCode: RoleCode): Future[Boolean] = {
    for {
      _ <- this.roleRepository.addUserRole(userId, roleCode)
    } yield true
  }
}
