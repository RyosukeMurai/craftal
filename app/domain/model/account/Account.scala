package domain.model.account

import domain.model.shared.Entity

class Account(val userId: Int,
              val name: String,
              val email: String,
              val activated: Boolean) extends Entity[Account] {
}
