package net.craftal.identityaccess.usecase

import javax.inject.{Inject, _}
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.{IdentityRepository, PasswordIdentity}
import net.craftal.identityaccess.domain.model.user.UserRepository

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class GetPasswordIdentity @Inject()(userRepository: UserRepository,
                                    identityRepository: IdentityRepository)
                                   (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int): Future[PasswordIdentity] = {
    for {
      u <- this.userRepository.findUser(userId)
      i <- this.identityRepository.findPasswordIdentity(u.id)
    } yield i
  }

  def execute(email: String): Future[PasswordIdentity] = {
    for {
      u <- this.userRepository.findUserByEmail(email).map {
        case Some(x) => x
        case None => throw new IllegalArgumentException("there is no user with the specified email address")
      }
      i <- this.identityRepository.findPasswordIdentity(u.id)
    } yield i
  }
}
