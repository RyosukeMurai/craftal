package net.craftal.core.usecase.attribute

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.attribute.{Attribute, AttributeRepository}

import scala.concurrent.Future

class GetAttributes @Inject()(repository: AttributeRepository) extends Interactor {

  def execute(idList: List[Int]): Future[List[Attribute]] = this.repository.findAttributesByIdList(idList)
}
