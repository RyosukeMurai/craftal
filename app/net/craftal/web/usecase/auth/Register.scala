package net.craftal.web.usecase.auth

import javax.inject.Inject
import net.craftal.common.usecase.Interactor
import net.craftal.identityaccess.api.AuthenticationService
import net.craftal.identityaccess.domain.model.role.RoleCode
import net.craftal.web.port.silhouette.SilhouetteServiceFacade
import notification.NotificationService
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class Register @Inject()(authenticationService: AuthenticationService,
                         notificationService: NotificationService,
                         silhouetteServiceFacade: SilhouetteServiceFacade)
                        (implicit ex: ExecutionContext) extends Interactor {
  def execute(email: String,
              name: String,
              password: String,
              roleCode: String,
              verificationExpiration: FiniteDuration = 5 minutes)
             (implicit request: Request[AnyContent], messages: Messages): Future[Boolean] = {
    this.authenticationService.getUser(email).flatMap {
      case Some(x) =>
        this.notificationService.notifyAlreadySignedUp(email, Option(x.name))
      case _ =>
        for {
          _ <- this.silhouetteServiceFacade.addPasswordAuthInfo(email, name, password) // register via silhouette
          u <- this.authenticationService.getUser(email).map {
            case Some(x) => x
            case None => throw new IllegalArgumentException("there is no user with the specified email address")
          }
          _ <- this.authenticationService.assumeRole(u.id, RoleCode.withName(roleCode))
          t <- this.authenticationService.createIdentityToken(email)
          result <- this.notificationService.notifySignUp(u.email, Option(u.name), t)
        } yield result
    }
  }
}
