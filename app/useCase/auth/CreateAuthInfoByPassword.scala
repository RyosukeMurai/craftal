package useCase.auth

import domain.model.auth.AuthRepository
import javax.inject._
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class CreateAuthInfoByPassword @Inject()(authRepository: AuthRepository)
                                        (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, hasher: String, hashedPassword: String, salt: String): Future[Int] =
    this.authRepository.createAuthByPassword(userId, hasher, hashedPassword, salt)
}
