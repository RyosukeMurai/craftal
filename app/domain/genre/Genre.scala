package domain.genre

import domain.shared.ValueObject

case class Genre(name: String,
                 parent: Option[Genre]) extends ValueObject[Genre] {
}
