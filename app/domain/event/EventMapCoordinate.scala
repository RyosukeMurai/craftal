package domain.event

import domain.shared.ValueObject

case class EventMapCoordinate(latitude: Long,
                              longitude: Long) extends ValueObject[EventMapCoordinate] {
}