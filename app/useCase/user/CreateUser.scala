package useCase.user

import domain.model.user.UserRepository
import javax.inject._
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CreateUser @Inject()(userRepository: UserRepository)
                          (implicit ex: ExecutionContext) extends Interactor {
  def execute(name: String, email: String): Future[Int] =
    this.userRepository.createUser(name = name, email = email)
}
