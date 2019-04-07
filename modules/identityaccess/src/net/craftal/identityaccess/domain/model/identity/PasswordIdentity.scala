package net.craftal.identityaccess.domain.model.identity

case class PasswordIdentity(userId: Int,
                            activated: Boolean,
                            hasher: String,
                            password: String) extends Identity {
}
