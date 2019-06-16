package net.craftal.web.usecase.genre

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.genre.Genre

import scala.concurrent.Future

class GetGenres @Inject()(domainService: DomainService) extends Interactor {

  def execute: Future[List[Genre]] = this.domainService.getGenres
}
