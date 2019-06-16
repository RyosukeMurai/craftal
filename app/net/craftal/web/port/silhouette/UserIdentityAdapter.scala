package net.craftal.web.port.silhouette

import net.craftal.identityaccess.domain.model.identity.Identity
import net.craftal.identityaccess.domain.model.role.Role
import net.craftal.identityaccess.domain.model.user.User

class UserIdentityAdapter(user: User, identity: Identity, role: Role)
  extends UserIdentity(
    user.id,
    Option(user.email),
    Option(user.name),
    Option(role.code.toString),
    identity.activated
  ) {
}