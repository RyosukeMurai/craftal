package net.craftal.identityaccess.domain.model.identity

case class PasswordIdentity(override val userId: Int,
                            override val activated: Boolean,
                            hasher: String,
                            password: String) extends Identity(userId, activated) {
}
