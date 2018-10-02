package domain.artist

import domain.user.User

class Artist(id: Int,
             name: String,
             email: String) extends User(id, name, email) {
}
