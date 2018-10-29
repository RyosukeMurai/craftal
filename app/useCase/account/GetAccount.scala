package useCase.account

import domain.model.account.{Account, AccountRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetAccount @Inject()(repository: AccountRepository) extends Interactor {

  def execute(id: Int): Future[Account] = this.repository.find(id)
}
