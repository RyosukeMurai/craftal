package useCase.auth

import javax.inject._

import domain.model.auth.AuthRepository
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class UpdatePassword @Inject()(authRepository: AuthRepository)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, hasher: String, hashedPassword: String): Future[Boolean] =
    this.authRepository.updatePassword(userId, hasher, hashedPassword)
}
