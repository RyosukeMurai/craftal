package net.craftal.identityaccess.usecase

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.role.RoleRepository
import net.craftal.identityaccess.domain.model.user.UserRepository

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class Register @Inject()(userRepository: UserRepository,
                         roleRepository: RoleRepository,
                         identityRepository: IdentityRepository)
                        (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String,
              hasher: String,
              password: String,
              name: Option[String] = None,
              verificationExpiration: FiniteDuration = 5 minutes): Future[Int] = {
    for {
      userId <- this.userRepository.createUser(name.getOrElse(""), email)
      _ <- this.identityRepository.createIdentityByPassword(userId, hasher, password)
    } yield userId
  }
}
