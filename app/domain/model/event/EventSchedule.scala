package domain.model.event

import java.util.Date

import domain.model.shared.ValueObject

case class EventSchedule(eventId: Int,
                         venue: String,
                         mapCoordinate: EventMapCoordinate,
                         stateTime: Date,
                         endTime: Date) extends ValueObject[EventSchedule] {
}