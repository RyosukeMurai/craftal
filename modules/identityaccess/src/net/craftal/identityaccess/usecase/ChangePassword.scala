package net.craftal.identityaccess.usecase

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.user.UserRepository

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class ChangePassword @Inject()(userRepository: UserRepository,
                               identityRepository: IdentityRepository)
                              (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int, currentPassword: String, newPassword: String): Future[Boolean] = {
    for {
      u <- this.userRepository.findUser(userId)
      //c <- this.credentialsProvider.authenticate(Credentials(u.email, currentPassword))
      //_ <- this.authInfoRepository.update[PasswordInfo](
      //  LoginInfo(CredentialsProvider.ID, c.providerKey),
      //  this.passwordHasherRegistry.current.hash(newPassword)
      //)
    } yield true
  }
}
