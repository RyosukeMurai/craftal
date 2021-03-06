package net.craftal.identityaccess.usecase

import java.util.UUID

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.UserRepository
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class CreateIdentityToken @Inject()(userRepository: UserRepository,
                                    identityRepository: IdentityRepository)
                                   (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String, verificationExpiration: FiniteDuration = 5 minutes): Future[UUID] =
    for {
      user <- this.userRepository.findUserByEmail(email)
      token <- this.identityRepository.createIdentityToken(
        user.get.id,
        UUID.randomUUID(),
        DateTime.now.withZone(DateTimeZone.UTC).plusSeconds(verificationExpiration.toSeconds.toInt)
      ).map(t => UUID.fromString(t))
    } yield token
}
