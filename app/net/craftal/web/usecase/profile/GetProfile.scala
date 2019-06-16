package net.craftal.web.usecase.profile

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.member.Member
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetProfile @Inject()(domainService: DomainService)
                          (implicit executionContext: ExecutionContext) extends Interactor {

  def execute(memberId: Int)
             (implicit request: Request[AnyContent], messages: Messages): Future[Member] =
    for {
      m <- this.domainService.getMember(memberId)
    } yield m
}