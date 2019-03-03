package net.craftal.identityaccess.domain.model.user

import net.craftal.common.domain.model.Entity


class User(val id: Int,
           var name: String,
           var email: String) extends Entity[User] {
}
