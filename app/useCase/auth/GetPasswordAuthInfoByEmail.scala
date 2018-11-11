package useCase.auth

import domain.model.auth.{PasswordAuthInfo, AuthRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetPasswordAuthInfoByEmail @Inject()(repository: AuthRepository) extends Interactor {

  def execute(email: String): Future[Option[PasswordAuthInfo]] =
    this.repository.findPasswordAuthInfoByEmail(email)
}
