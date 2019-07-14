package net.craftal.core.domain.model.eventer

import net.craftal.common.domain.model.ValueObject

case class EventerPhoto(photoId: Int,
                        positionNo: Int) extends ValueObject[EventerPhoto] {
}