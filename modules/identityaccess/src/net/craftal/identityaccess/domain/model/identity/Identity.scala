package net.craftal.identityaccess.domain.model.identity

abstract case class Identity(userId: Int,
                             activated: Boolean) extends Entity[Identity] {
}
