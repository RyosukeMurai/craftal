package net.craftal.identityaccess.usecase

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.user.{User, UserRepository}

import scala.concurrent.Future

class GetUser @Inject()(repository: UserRepository) extends Interactor {

  def execute(userId: Int): Future[User] = this.repository.findUser(userId)

  def execute(email: String): Future[Option[User]] = this.repository.findUserByEmail(email)
}
