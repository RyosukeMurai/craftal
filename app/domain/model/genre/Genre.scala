package domain.model.genre

import domain.model.shared.ValueObject

case class Genre(id: Int, //TODO(RyosukeMurai): Should i remove "id" on value object ? => use "code" ?
                 name: String,
                 parent: Option[Genre]) extends ValueObject[Genre] { //TODO(RyosukeMurai): Should i use Entity?
}
