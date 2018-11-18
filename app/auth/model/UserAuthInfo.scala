package auth.model

import com.mohiva.play.silhouette.api.Identity

case class UserAuthInfo(id: Int,
                        email: Option[String],
                        name: Option[String],
                        activated: Boolean) extends Identity {

}
