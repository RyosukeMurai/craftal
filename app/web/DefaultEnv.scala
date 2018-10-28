package web

import com.mohiva.play.silhouette.api.Env
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import web.model.auth.UserIdentity

/**
  * The default env.
  */
trait DefaultEnv extends Env {
  type I = UserIdentity
  type A = CookieAuthenticator
}
