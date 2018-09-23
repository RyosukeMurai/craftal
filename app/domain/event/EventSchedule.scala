package domain.event

import java.util.Date

import domain.shared.ValueObject

case class EventSchedule(eventId: Int,
                         venue: String,
                         mapCoordinate: EventMapCoordinate,
                         stateTime: Date,
                         endTime: Date) extends ValueObject[EventSchedule] {
}