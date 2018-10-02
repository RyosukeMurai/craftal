package presentation.mapper

import domain.artist.Artist
import domain.event._
import presentation.model.event.EventDetail


object EventDetailDataMapper {
  def transform(event: Event, artists: List[Artist]): EventDetail =
    new EventDetail(event.id, event.title, event.description, event.status, event.location, event.getSchedule, artists)
}
