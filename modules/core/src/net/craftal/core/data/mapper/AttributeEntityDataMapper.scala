package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.attribute._

object AttributeEntityDataMapper {

  def transform(attributeEntity: Tables.AttributeRow): Attribute =
    Attribute(
      id = attributeEntity.id,
      name = attributeEntity.name,
      attributeType = AttributeType.apply(attributeEntity.typeId)
    )

  def transformCollection(attributeRows: List[Tables.AttributeRow]): List[Attribute] =
    attributeRows.map(m => this.transform(m))

}
