package net.craftal.core.domain.model.event

import net.craftal.common.domain.model.ValueObject

case class EventMapCoordinate(latitude: Long,
                              longitude: Long) extends ValueObject[EventMapCoordinate] {
}
