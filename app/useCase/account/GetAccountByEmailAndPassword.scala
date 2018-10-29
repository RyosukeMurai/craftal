package useCase.account

import domain.model.account.{Account, AccountRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetAccountByEmailAndPassword @Inject()(repository: AccountRepository) extends Interactor {

  def execute(email: String, password: String /* encrypted password */): Future[Option[Account]] =
    this.repository.findByEmailAndPassword(email, password)
}
