package net.craftal.identityaccess.domain.model.role

object RoleCode extends Enumeration {
  type RoleCode = Value
  val administrator, eventer, artist, member = Value
}
