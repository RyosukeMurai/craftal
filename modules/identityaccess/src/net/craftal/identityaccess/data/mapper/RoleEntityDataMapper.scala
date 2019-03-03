package net.craftal.identityaccess.data.mapper

import net.craftal.common.data.Tables
import net.craftal.identityaccess.domain.model.role.Role

object RoleEntityDataMapper {
  def transform(roleRow: Tables.RoleRow): Role =
    Role(
      id = roleRow.id,
      name = roleRow.name,
      code = roleRow.code
    )
}
