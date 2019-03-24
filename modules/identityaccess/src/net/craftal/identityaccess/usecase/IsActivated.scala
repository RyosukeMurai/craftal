package net.craftal.identityaccess.usecase

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.UserRepository

import scala.concurrent.{ExecutionContext, Future}

class IsActivated @Inject()(userRepository: UserRepository,
                            identityRepository: IdentityRepository)(implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int): Future[Boolean] =
    this.identityRepository.findPasswordIdentity(userId).map(_.activated)
}

