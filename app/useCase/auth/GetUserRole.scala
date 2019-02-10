package useCase.auth

import domain.model.auth.{AuthRepository, Role}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetUserRole @Inject()(repository: AuthRepository) extends Interactor {
  def execute(userId: Int): Future[Role] = this.repository.findUserRole(userId)
}
