package net.craftal.core.domain.model.event

import net.craftal.common.domain.model.ValueObject
import org.joda.time.DateTime

case class EventSchedule(venue: String,
                         mapCoordinate: EventMapCoordinate,
                         startTime: DateTime,
                         endTime: DateTime,
                         prefectureId: Int,
                         address: String,
                         postalCode: String,
                         howToAccess: String,
                         venueUrl: Option[String],
                         venueRemarks: String) extends ValueObject[EventSchedule] {
}
