package net.craftal.core.domain.model.genre

import net.craftal.common.domain.model.Entity

case class Genre(id: Int,
                 name: String,
                 parent: Option[Genre]) extends Entity[Genre] {
}
