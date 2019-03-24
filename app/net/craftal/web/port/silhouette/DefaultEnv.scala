package net.craftal.web.port.silhouette

import com.mohiva.play.silhouette.api.Env
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator

trait DefaultEnv extends Env {
  type I = UserIdentity
  type A = CookieAuthenticator
}
