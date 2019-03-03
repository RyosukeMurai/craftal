package net.craftal.core.domain.model.photo

import net.craftal.common.domain.model.Entity

case class Photo(id: Int,
                 identifier: String,
                 name: String,
                 userId: Int) extends Entity[Photo] {
}
