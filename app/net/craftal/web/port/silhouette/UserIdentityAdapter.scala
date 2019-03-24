package net.craftal.web.port.silhouette

import net.craftal.identityaccess.domain.model.identity.Identity
import net.craftal.identityaccess.domain.model.user.User
import UserIdentity

case class UserIdentityAdapter(user: User, identity: Identity)
  extends UserIdentity(user.id, Option(user.email), Option(user.name), identity.activated) {

}