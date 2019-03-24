package net.craftal.identityaccess.domain.model.identity

import net.craftal.common.domain.model.Entity

abstract case class Identity(userId: Int,
                             activated: Boolean) extends Entity[Identity] {
}
