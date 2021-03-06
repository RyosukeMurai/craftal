package net.craftal.core.domain.model.member

import scala.concurrent.Future

trait MemberRepository {

  def findMember(memberId: Int): Future[Member]

  def findMembersByKeyword(keyword: Option[String]): Future[List[Member]]

  def findAll(): Future[List[Member]]

  def updateMemberProfile(memberId: Int,
                          name: Option[String],
                          prefectureId: Option[Int]): Future[Member]
}
