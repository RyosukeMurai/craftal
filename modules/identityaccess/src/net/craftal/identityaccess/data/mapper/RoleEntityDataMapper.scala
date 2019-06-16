package net.craftal.identityaccess.data.mapper

import net.craftal.common.data.Tables
import net.craftal.identityaccess.domain.model.role.{Role, RoleCode}

object RoleEntityDataMapper {
  def transform(roleRow: Tables.RoleRow): Role =
    Role(
      id = roleRow.id,
      name = roleRow.name,
      code = RoleCode.withName(roleRow.code)
    )
}
