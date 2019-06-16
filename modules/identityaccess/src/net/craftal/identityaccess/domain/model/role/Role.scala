package net.craftal.identityaccess.domain.model.role

import net.craftal.common.domain.model.Entity
import net.craftal.identityaccess.domain.model.role.RoleCode.RoleCode

case class Role(id: Int,
                name: String,
                code: RoleCode) extends Entity[Role] {
}
