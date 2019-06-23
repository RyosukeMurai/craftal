package net.craftal.core.usecase.member

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.member.{Member, MemberRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class UpdateMemberProfile @Inject()(memberRepository: MemberRepository)
                                   (implicit ex: ExecutionContext) extends Interactor {
  def execute(memberId: Int,
              name: Option[String],
              prefectureId: Option[Int]): Future[Member] =
    this.memberRepository.updateMemberProfile(
      memberId,
      name,
      prefectureId
    )
}
