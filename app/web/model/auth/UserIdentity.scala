package web.model.auth

import com.mohiva.play.silhouette.api.{Identity, LoginInfo}

case class UserIdentity(id: Int,
                        email: Option[String],
                        name: Option[String],
                        loginInfo: LoginInfo,
                        activated: Boolean) extends Identity {

}
