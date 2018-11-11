package useCase.auth

import domain.model.auth.{PasswordAuthInfo, AuthRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetPasswordAuthInfo @Inject()(repository: AuthRepository) extends Interactor {

  def execute(id: Int): Future[PasswordAuthInfo] = this.repository.findPasswordAuthInfo(id)
}
