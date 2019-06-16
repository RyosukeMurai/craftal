package net.craftal.web.usecase.prefecture

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.prefecture.Prefecture

import scala.concurrent.Future

class GetPrefectures @Inject()(domainService: DomainService) extends Interactor {

  def execute: Future[List[Prefecture]] = this.domainService.getPrefectures

  def execute(idList: List[Int]): Future[List[Prefecture]] = this.domainService.getPrefectures(idList)
}
