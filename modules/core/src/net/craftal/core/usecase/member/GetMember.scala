package net.craftal.core.usecase.member

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.member.{Member, MemberRepository}

import scala.concurrent.Future

class GetMember @Inject()(repository: MemberRepository) extends Interactor {

  def execute(memberId: Int): Future[Member] = this.repository.findMember(memberId)
}
