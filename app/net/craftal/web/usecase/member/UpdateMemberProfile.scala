package net.craftal.web.usecase.member

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.member.Member
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class UpdateMemberProfile @Inject()(domainService: DomainService)
                                   (implicit ex: ExecutionContext) extends Interactor {

  def execute(memberId: Int,
              name: Option[String],
              prefectureId: Option[Int])
             (implicit request: Request[AnyContent], messages: Messages): Future[Member] =
    this.domainService.updateMemberProfile(
      memberId,
      name,
      prefectureId
    )
}
