package net.craftal.core.domain.model.attribute

import scala.concurrent.Future

trait AttributeRepository {

  def findAttribute(attributeId: Int): Future[Attribute]

  def findAttributesByIdList(idList: List[Int]): Future[List[Attribute]]
}
