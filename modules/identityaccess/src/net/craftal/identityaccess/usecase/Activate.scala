package net.craftal.identityaccess.usecase

import java.util.UUID

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.UserRepository

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class Activate @Inject()(userRepository: UserRepository,
                         identityRepository: IdentityRepository)(implicit ex: ExecutionContext) extends Interactor {

  def execute(token: UUID): Future[Boolean] = {
    for {
      user <- this.userRepository.findUserByToken(token)
      result <- this.identityRepository.updateUserIdentity(user.get.id, isActivated = true)
    } yield result
  }
}
