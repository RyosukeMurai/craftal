package domain.event

import domain.shared.ValueObject

case class EventPhoto(eventId: Int,
                      photoId: Int,
                      positionNo: Int) extends ValueObject[EventPhoto] {
}