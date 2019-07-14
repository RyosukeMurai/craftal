package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.eventer.Eventer

object EventerEntityDataMapper {
  def transform(entity: Tables.UserRow): Eventer =
    Eventer(
      id = entity.id,
      name = entity.name,
      email = entity.email,
    )

  def transformCollection(entities: List[Tables.UserRow]): List[Eventer] = {
    entities.map(m => this.transform(m))
  }

}
