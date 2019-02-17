package useCase.auth

import auth.model.UserAuthInfo
import domain.model.auth.{AuthRepository, Role}
import domain.model.user.{User, UserRepository}
import javax.inject._
import useCase.Interactor

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetAuthInfoByEmail @Inject()(userRepository: UserRepository,
                                   authRepository: AuthRepository)(implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String): Future[Option[UserAuthInfo]] =
    this.userRepository
      .findByEmail(email)
      .flatMap {
        case Some(u) => this.authRepository.findUserRole(u.id).map {
          r => Some(UserAuthInfo(u.id, Option(u.email), Option(u.name), Option(r), activated = true))
        }
        case None => Future.successful(None)
      }
}

