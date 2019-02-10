package web.model.auth

import be.objectify.deadbolt.scala.models.Subject

class AuthSubject(val userName: String,
                  val roleCodes: List[String],
                  val permissionCodes: List[String]) extends Subject {
  override def roles: List[AuthRole] = roleCodes.map(AuthRole)

  override def permissions: List[AuthPermission] = permissionCodes.map(AuthPermission)

  override def identifier: String = userName
}
