package presentation.model

import java.util.Date

import domain.event.EventLocation.EventLocation
import domain.event.EventStatus.EventStatus
import domain.shared.Entity

class EventCalendar(var id: Int,
                    var title: String,
                    var description: String,
                    var status: EventStatus,
                    var location: EventLocation,
                    var createdAt: Date,
                    var updatedAt: Date) extends Entity {
}
