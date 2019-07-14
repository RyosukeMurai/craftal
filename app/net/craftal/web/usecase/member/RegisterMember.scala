package net.craftal.web.usecase.member

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.member.Member
import net.craftal.identityaccess.domain.model.role.RoleCode
import net.craftal.web.usecase.auth.Register
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class RegisterMember @Inject()(domainService: DomainService,
                               register: Register)
                              (implicit ex: ExecutionContext) extends Interactor {

  def execute(email: String,
              name: String,
              password: String,
              verificationExpiration: FiniteDuration = 5 minutes)
             (implicit request: Request[AnyContent], messages: Messages): Future[Member] = {
    this.register
      .execute(email, name, password, RoleCode.member.toString, verificationExpiration)
      .flatMap(u => {
        this.domainService.getMember(memberId = u.id)
      })
  }
}
