package domain.model.event

import domain.model.shared.ValueObject

case class EventMapCoordinate(latitude: Long,
                              longitude: Long) extends ValueObject[EventMapCoordinate] {
}