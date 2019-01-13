package useCase.user

import javax.inject._

import domain.model.auth.AuthRepository
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UpdateUserAuth @Inject()(authRepository: AuthRepository)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, isActivated: Boolean): Future[Boolean] =
    this.authRepository.updateUserAuth(userId = userId, isActivated = isActivated)
}
