package web.silhouette

import auth.model.UserAuthInfo
import com.mohiva.play.silhouette.api.Env
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator

/**
  * The default env.
  */
trait DefaultEnv extends Env {
  type I = UserAuthInfo
  type A = CookieAuthenticator
}
