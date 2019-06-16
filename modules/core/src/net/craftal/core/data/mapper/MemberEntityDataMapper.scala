package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.member.Member

object MemberEntityDataMapper {
  def transform(entity: Tables.UserRow): Member =
    Member(
      id = entity.id,
      name = entity.name,
      email = entity.email,
      prefectureId = 13, //TODO(RyosukeMurai): save prefecture id with member
    )

  def transformCollection(entities: List[Tables.UserRow]): List[Member] = {
    entities.map(m => this.transform(m))
  }

}
