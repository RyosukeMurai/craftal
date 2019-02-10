package data.mapper

import data.Tables
import domain.model.auth.Role

object RoleEntityDataMapper {
  def transform(roleRow: Tables.RoleRow): Role =
    Role(
      id = roleRow.id,
      name = roleRow.name,
      code = roleRow.code
    )
}
