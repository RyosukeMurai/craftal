package net.craftal.core.domain.model.attribute

import net.craftal.common.domain.model.Entity
import net.craftal.core.domain.model.attribute.AttributeType.AttributeType

case class Attribute(id: Int,
                     name: String,
                     attributeType: AttributeType) extends Entity[Attribute] {
}
