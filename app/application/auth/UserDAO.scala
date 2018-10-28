package application.auth

import application.auth.UserDAO._
import com.mohiva.play.silhouette.api.LoginInfo
import web.model.auth.UserIdentity

import scala.collection.mutable
import scala.concurrent.Future

/**
  * Give access to the user object.
  */
class UserDAO {

  /**
    * Finds a user by its login info.
    *
    * @param loginInfo The login info of the user to find.
    * @return The found user or None if no user for the given login info could be found.
    */
  def find(loginInfo: LoginInfo): Future[Option[UserIdentity]] = Future.successful(
    users.find { case (_, user) => user.loginInfo == loginInfo }.map(_._2)
  )

  /**
    * Finds a user by its user ID.
    *
    * @param userID The ID of the user to find.
    * @return The found user or None if no user for the given ID could be found.
    */
  def find(userID: Int): Future[Option[UserIdentity]] = Future.successful(users.get(userID))

  /**
    * Saves a user.
    *
    * @param user The user to save.
    * @return The saved user.
    */
  def save(user: UserIdentity): Future[UserIdentity] = {
    users += (user.id -> user)
    Future.successful(user)
  }
}

/**
  * The companion object.
  */
object UserDAO {

  /**
    * The list of users.
    */
  val users: mutable.HashMap[Int, UserIdentity] = mutable.HashMap()
}
