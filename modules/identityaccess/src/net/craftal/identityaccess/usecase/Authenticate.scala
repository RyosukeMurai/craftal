package net.craftal.identityaccess.usecase

import javax.inject.{Inject, _}
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.{User, UserRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class Authenticate @Inject()(userRepository: UserRepository,
                             identityRepository: IdentityRepository)
                            (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String, password: String): Future[User] = {
    for {
      u <- this.userRepository.findUserByEmail(email).map {
        case Some(x) => x
        case None => throw new IllegalArgumentException("there is no user with the specified email address")
      }
      i <- this.identityRepository.findPasswordIdentity(u.id)
    } yield {
      if (i.password != password) {
        throw new IllegalArgumentException("there is no user with the specified password")
      }
      if (!i.activated) {
        throw new VerifyError("specified user has not been activated")
      }
      u
    }
  }
}
