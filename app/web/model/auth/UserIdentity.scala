package web.model.auth

import com.mohiva.play.silhouette.api.{Identity, LoginInfo}

case class UserIdentity(id: Int,
                        email: Option[String],
                        name: Option[String],
                        activated: Boolean) extends Identity {

  def loginInfo: LoginInfo = {
    this.email match {
      case Some(x) => LoginInfo(x, "email") //TODO(RyosukeMurai): must use env parameter to specified login key
      case None => throw new IllegalAccessException(s"Account ($this.name) doesn't have email.")
    }
  }
}
