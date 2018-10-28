package useCase.user

import domain.model.user.{User, UserRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetUser @Inject()(repository: UserRepository) extends Interactor {

  def execute(id: Int): Future[User] = this.repository.find(id)
}
