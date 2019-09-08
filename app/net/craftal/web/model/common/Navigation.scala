package net.craftal.web.model.common

import play.api.mvc.Call

case class Navigation(name: String, action: Call, icon: Option[String] = None) {

}
