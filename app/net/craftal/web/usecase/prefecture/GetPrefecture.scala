package net.craftal.web.usecase.prefecture

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.prefecture.Prefecture

import scala.concurrent.Future

class GetPrefecture @Inject()(domainService: DomainService) extends Interactor {

  def execute(prefectureId: Int): Future[Prefecture] = this.domainService.getPrefecture(prefectureId)
}
