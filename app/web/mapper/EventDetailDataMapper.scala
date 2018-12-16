package web.mapper

import domain.model.artist.Artist
import domain.model.event._
import domain.model.photo.Photo
import web.model.event.EventDetail


object EventDetailDataMapper {
  def transform(event: Event, photos: Seq[Photo], artists: Seq[Artist]): EventDetail =
    new EventDetail(
      event.id, event.title, event.description, event.status, event.location, event.getSchedule, photos, artists
    )
}
