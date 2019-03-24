package net.craftal.web.port.silhouette

import com.mohiva.play.silhouette.api.Identity

case class UserIdentity(id: Int,
                        email: Option[String],
                        name: Option[String],
                        activated: Boolean) extends Identity {

}
