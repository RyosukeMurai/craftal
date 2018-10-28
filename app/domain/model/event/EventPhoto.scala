package domain.model.event

import domain.model.shared.ValueObject

case class EventPhoto(eventId: Int,
                      photoId: Int,
                      positionNo: Int) extends ValueObject[EventPhoto] {
}