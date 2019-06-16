package net.craftal.core.usecase.attribute

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.attribute.{Attribute, AttributeRepository}

import scala.concurrent.Future

class GetAttribute @Inject()(repository: AttributeRepository) extends Interactor {

  def execute(attributeId: Int): Future[Attribute] = this.repository.findAttribute(attributeId)
}
