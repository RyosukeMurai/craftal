package useCase.auth

import java.util.UUID

import domain.model.auth.{AuthRepository, AuthToken}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetAuthToken @Inject()(repository: AuthRepository) extends Interactor {
  def execute(token: UUID): Future[Option[AuthToken]] = this.repository.findAuthToken(token)
}
