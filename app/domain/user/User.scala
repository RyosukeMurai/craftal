package domain.user

import domain.shared.Entity

class User(val id: Int,
           var name: String,
           var email: String) extends Entity[User] {
}
