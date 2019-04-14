package net.craftal.identityaccess.usecase

import java.util.UUID

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.{User, UserRepository}
import org.joda.time.DateTime

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class Authenticate @Inject()(userRepository: UserRepository,
                             identityRepository: IdentityRepository)
                            (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String): Future[User] = {
    for {
      u <- this.userRepository.findUserByEmail(email).map {
        case Some(x) => x
        case None => throw new IllegalArgumentException("there is no user with the specified email address")
      }
      i <- this.identityRepository.findPasswordIdentity(u.id)
    } yield {
      //Responsibility for password checking is delegated to silhouette
      if (!i.activated) {
        throw new VerifyError("specified user has not been activated")
      }
      u
    }
  }

  def execute(token: UUID): Future[User] = {
    for {
      t <- this.identityRepository.findIdentityTokenDetail(token).map {
        case Some(x) if x.expiry.isAfter(new DateTime()) => x
        case Some(_) => throw new VerifyError("The token is already expired")
        case None => throw new IllegalArgumentException("The passed token is illegal")
      }
      u <- this.userRepository.findUserByToken(t.token).map {
        case Some(x) => x
        case None => throw new IllegalArgumentException("The passed token is illegal")
      }
    } yield u
  }
}
