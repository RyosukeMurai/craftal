package domain.model.auth

import domain.model.shared.Entity

case class Role(id: Int,
                name: String,
                code: String) extends Entity[Role] {
}
