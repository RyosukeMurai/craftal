package web.model.auth

import com.mohiva.play.silhouette.api.Identity

case class UserIdentity(id: Int,
                        email: Option[String],
                        name: Option[String],
                        activated: Boolean) extends Identity {

}
