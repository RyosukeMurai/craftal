package net.craftal.web.port.deadbolt

import be.objectify.deadbolt.scala.models.Subject

case class CustomSubject(userName: String,
                         roleCodes: List[String],
                         permissionCodes: List[String]) extends Subject {
  override def roles: List[CustomRole] = roleCodes.map(CustomRole)

  override def permissions: List[CustomPermission] = permissionCodes.map(CustomPermission)

  override def identifier: String = userName
}
