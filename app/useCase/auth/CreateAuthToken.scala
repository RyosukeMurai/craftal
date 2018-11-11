package useCase.auth

import java.util.UUID

import domain.model.auth.AuthRepository
import javax.inject._
import org.joda.time.{DateTime, DateTimeZone}
import useCase.Interactor

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class CreateAuthToken @Inject()(authRepository: AuthRepository)
                               (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, verificationExpiration: FiniteDuration = 5 minutes): Future[UUID] =
    this.authRepository.createAuthToken(
      UUID.randomUUID(),
      userId,
      DateTime.now.withZone(DateTimeZone.UTC).plusSeconds(verificationExpiration.toSeconds.toInt)
    ).map(t => UUID.fromString(t))
}
