package domain.model.user

import domain.model.shared.Entity

class User(val id: Int,
           var name: String,
           var email: String) extends Entity[User] {
}
