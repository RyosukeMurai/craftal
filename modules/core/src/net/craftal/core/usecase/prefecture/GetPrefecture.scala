package net.craftal.core.usecase.prefecture

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.prefecture.{Prefecture, PrefectureRepository}

import scala.concurrent.Future

class GetPrefecture @Inject()(repository: PrefectureRepository) extends Interactor {

  def execute(prefectureId: Int): Future[Prefecture] = this.repository.findPrefecture(prefectureId)
}
