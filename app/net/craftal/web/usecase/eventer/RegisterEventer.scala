package net.craftal.web.usecase.eventer

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.eventer.Eventer
import net.craftal.identityaccess.domain.model.role.RoleCode
import net.craftal.web.usecase.auth.Register
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class RegisterEventer @Inject()(domainService: DomainService,
                                register: Register)
                               (implicit ex: ExecutionContext) extends Interactor {

  def execute(email: String,
              name: String,
              password: String,
              verificationExpiration: FiniteDuration = 5 minutes)
             (implicit request: Request[AnyContent], messages: Messages): Future[Eventer] = {
    this.register
      .execute(email, name, password, RoleCode.eventer.toString, verificationExpiration)
      .flatMap(u => {
        this.domainService.registerEventer(userId = u.id)
      })
  }
}
