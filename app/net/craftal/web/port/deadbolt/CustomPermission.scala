package net.craftal.web.port.deadbolt

import be.objectify.deadbolt.scala.models.Permission

case class CustomPermission(value: String) extends Permission
