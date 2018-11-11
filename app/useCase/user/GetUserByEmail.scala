package useCase.user

import domain.model.user.{User, UserRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetUserByEmail @Inject()(repository: UserRepository) extends Interactor {

  def execute(email: String): Future[Option[User]] =
    this.repository.findByEmail(email)
}
