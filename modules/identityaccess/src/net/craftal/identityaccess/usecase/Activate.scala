package net.craftal.identityaccess.usecase

import java.util.UUID

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.UserRepository
import org.joda.time.DateTime

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class Activate @Inject()(userRepository: UserRepository,
                         identityRepository: IdentityRepository)(implicit ex: ExecutionContext) extends Interactor {

  def execute(token: UUID): Future[Boolean] = {
    for {
      user <- this.userRepository.findUserByToken(token).map {
        case Some(x) => x
        case None => throw new IllegalArgumentException("The passed token is illegal")
      }
      result <- this.identityRepository.findIdentityTokenDetail(token).flatMap {
        case Some(x) if x.expiry.isAfter(new DateTime()) => this.identityRepository.updateUserIdentity(user.id, isActivated = true)
        case Some(_) => throw new VerifyError("The token is already expired")
        case None => throw new IllegalArgumentException("The passed token is illegal")
      }
    } yield result
  }
}
