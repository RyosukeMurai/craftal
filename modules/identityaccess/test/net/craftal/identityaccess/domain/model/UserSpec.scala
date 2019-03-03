package net.craftal.identityaccess.domain.model

import net.craftal.identityaccess.domain.model.user.User
import org.specs2.mutable.Specification

class UserSpec extends Specification {

  "User" should {
    "be instance" in {
      val user = new User(1, "test", "murai@ryosuke.com")
      user.name must_== "test"
    }
  }

}
