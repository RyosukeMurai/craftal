package net.craftal.identityaccess.usecase

import java.util.UUID

import javax.inject.{Inject, _}
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.UserRepository
import org.joda.time.DateTime

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class ResetPassword @Inject()(userRepository: UserRepository,
                              identityRepository: IdentityRepository)
                             (implicit ex: ExecutionContext) extends Interactor {
  def execute(token: UUID, password: String /*, providerID == CredentialsProvider.ID */): Future[Boolean] = {
    for {
      u <- this.userRepository.findUserByToken(token).map {
        case Some(x) => x
        case None => throw new IllegalArgumentException("The passed token is illegal")
      }
      _ <- this.identityRepository.findIdentityTokenDetail(token).map {
        case x if x.expiry.isAfter(new DateTime()) => this.identityRepository.updatePassword(u.id, "", "")
        case _ => throw new VerifyError("The token is already expired")
      }
    } yield true
  }
}
