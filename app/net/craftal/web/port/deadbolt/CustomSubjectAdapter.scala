package net.craftal.web.port.deadbolt

import net.craftal.web.port.silhouette.UserIdentity

class CustomSubjectAdapter(userIdentity: UserIdentity)
  extends CustomSubject(
    userName = userIdentity.name.getOrElse(""),
    roleCodes = userIdentity.roleCode.map(r => List(r)).getOrElse(List()),
    permissionCodes = List("")
  ) {

}