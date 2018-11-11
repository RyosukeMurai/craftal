package domain.model.user

import domain.model.shared.Entity

class User(val id: Int,
           var name: String,
           var email: String,
           var activated: Boolean = false) extends Entity[User] {
}
