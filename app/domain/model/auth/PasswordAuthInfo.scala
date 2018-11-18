package domain.model.auth

import domain.model.shared.Entity

case class PasswordAuthInfo(userId: Int,
                            hasher: String,
                            password: String,
                            activated: Boolean) extends Entity[PasswordAuthInfo] {
}
