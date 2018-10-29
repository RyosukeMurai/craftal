package useCase.account

import domain.model.account.{Account, AccountRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetAccountByEmail @Inject()(repository: AccountRepository) extends Interactor {

  def execute(email: String): Future[Option[Account]] =
    this.repository.findByEmail(email)
}
