package domain.user.interactor

import domain.shared.Interactor
import domain.user.{User, UserRepository}
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetUser @Inject()(repository: UserRepository) extends Interactor {

  def execute(id: Int): Future[User] = this.repository.find(id)
}
