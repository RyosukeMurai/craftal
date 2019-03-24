package net.craftal.web.model.auth

import be.objectify.deadbolt.scala.models.Permission

case class AuthPermission(value: String) extends Permission
