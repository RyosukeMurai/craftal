package useCase.user

import java.util.UUID
import javax.inject._

import domain.model.user.{User, UserRepository}
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetUserByToken @Inject()(repository: UserRepository) extends Interactor {

  def execute(token: UUID): Future[Option[User]] =
    this.repository.findByToken(token)
}
