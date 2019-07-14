package net.craftal.core.usecase.eventer

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.eventer.{Eventer, EventerRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class RegisterEventer @Inject()(eventerRepository: EventerRepository)
                               (implicit ex: ExecutionContext) extends Interactor {
  def execute(userId: Int): Future[Eventer] =
    this.eventerRepository.createEventer(userId)
}
