package net.craftal.identityaccess.usecase

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.UserRepository

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class ChangePassword @Inject()(userRepository: UserRepository,
                               identityRepository: IdentityRepository)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, hasher: String, hashedPassword: String): Future[Boolean] = {
    for {
      _ <- this.identityRepository.updatePassword(userId, hasher, hashedPassword)
    } yield true
  }
}
