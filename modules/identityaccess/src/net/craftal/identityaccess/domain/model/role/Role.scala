package net.craftal.identityaccess.domain.model.role

import net.craftal.common.domain.model.Entity

case class Role(id: Int,
                name: String,
                code: String) extends Entity[Role] {
}
