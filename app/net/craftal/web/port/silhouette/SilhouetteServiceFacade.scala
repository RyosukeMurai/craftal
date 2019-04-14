package net.craftal.web.port.silhouette


import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.util.{Credentials, PasswordHasherRegistry, PasswordInfo}
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import javax.inject.Inject
import net.craftal.identityaccess.api.AuthenticationService

import scala.concurrent.{ExecutionContext, Future}

class SilhouetteServiceFacade @Inject()(authenticationService: AuthenticationService,
                                        userIdentityService: UserIdentityService,
                                        passwordHasherRegistry: PasswordHasherRegistry,
                                        credentialsProvider: CredentialsProvider,
                                        authInfoRepository: AuthInfoRepository)
                                       (implicit ex: ExecutionContext) {

  def addPasswordAuthInfo(email: String, name: String, password: String): Future[PasswordInfo] = { //TODO(RyosukeMurai): define password response data type
    for {
      r <- authInfoRepository.add(
        LoginInfo(CredentialsProvider.ID, email),
        passwordHasherRegistry.current.hash(password),
      )
    } yield r
  }

  def updatePasswordAuthInfo(email: String, password: String): Future[PasswordInfo] = {
    for {
      r <- authInfoRepository.update(
        LoginInfo(CredentialsProvider.ID, email),
        passwordHasherRegistry.current.hash(password)
      )
    } yield r
  }

  def updatePasswordAuthInfo(email: String, currentPassword: String, newPassword: String): Future[PasswordInfo] = {
    for {
      c <- credentialsProvider.authenticate(Credentials(email, currentPassword))
      r <- authInfoRepository.update[PasswordInfo](
        LoginInfo(CredentialsProvider.ID, c.providerKey),
        passwordHasherRegistry.current.hash(newPassword)
      )
    } yield r
  }

  def authenticate(email: String, password: String): Future[LoginInfo] = {
    credentialsProvider.authenticate(Credentials(email, password))
  }
}

