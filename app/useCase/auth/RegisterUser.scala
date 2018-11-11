package useCase.auth

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.util.PasswordHasherRegistry
import domain.model.auth.AuthRepository
import domain.model.user.UserRepository
import javax.inject.{Inject, _}
import org.joda.time.{DateTime, DateTimeZone}
import useCase.Interactor
import useCase.auth.reponse.AccountRegistrationResponse
import web.model.auth.UserIdentity

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class RegisterUser @Inject()(userRepository: UserRepository,
                             accountRepository: AuthRepository,
                             passwordHasherRegistry: PasswordHasherRegistry, //TODO(RyosukeMurai): move to infra layer
                             authInfoRepository: AuthInfoRepository)
                            (implicit ex: ExecutionContext) extends Interactor {
  def execute(name: String, loginInfo: LoginInfo, password: String, verificationExpiration: FiniteDuration = 5 minutes): Future[AccountRegistrationResponse] = {
    // TODO(RyosukeMurai): to be one transaction (= do not separate table)
    // TODO(RyosukeMurai): have to catch unique key exception
    // TODO(RyosukeMurai): remove dependency of silhouette (e.g. loginInfo parameter)
    val passwordInfo = this.passwordHasherRegistry.current.hash(password)
    for {
      userId <- this.userRepository.createUser(name, loginInfo.providerKey)
      user <- this.userRepository.find(userId)
      _ <- this.authInfoRepository.add(loginInfo, passwordInfo)
      token <- this.accountRepository.createAuthToken(
        UUID.randomUUID(), userId, DateTime.now.withZone(DateTimeZone.UTC).plusSeconds(verificationExpiration.toSeconds.toInt)
      )
    } yield AccountRegistrationResponse(UserIdentity(userId, Option(user.email), Option(user.name), activated = false), token)
  }
}
