package web.mapper

import domain.model.artist.Artist
import domain.model.event._
import web.model.event.EventDetail


object EventDetailDataMapper {
  def transform(event: Event, artists: List[Artist]): EventDetail =
    new EventDetail(event.id, event.title, event.description, event.status, event.location, event.getSchedule, artists)
}
