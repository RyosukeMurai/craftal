package auth.model

import com.mohiva.play.silhouette.api.Identity
import domain.model.auth.Role

case class UserAuthInfo(id: Int,
                        email: Option[String],
                        name: Option[String],
                        role: Option[Role],
                        activated: Boolean) extends Identity {

}
