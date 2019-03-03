package net.craftal.core.domain.model.event

import net.craftal.common.domain.model.ValueObject

case class EventPhoto(photoId: Int,
                      positionNo: Int) extends ValueObject[EventPhoto] {
}
