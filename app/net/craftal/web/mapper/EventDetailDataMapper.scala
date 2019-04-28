package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.artist.ArtistDescriptor
import net.craftal.web.model.event.{EventDetail, EventScheduleDescriptor}
import net.craftal.web.translator.common.DateTimeTranslator
import net.craftal.web.translator.event.EventLocationTranslator
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}


object EventDetailDataMapper extends DataMapper {
  def transform(event: Event, photos: List[Photo], prefectures: List[Prefecture], artists: List[Artist])
               (implicit request: Request[AnyContent], messages: Messages): EventDetail = {
    println(event)
    EventDetail(
      event.id,
      event.title,
      event.description,
      event.status,
      event.schedule.map(s =>
        EventScheduleDescriptor(
          prefectures.find(_.id == s.prefectureId).get.name,
          EventLocationTranslator.translate(event.location),
          DateTimeTranslator.translate(s.startTime, s.endTime),
          s.venue
        )
      ).toList,
      PhotoDescriptorDataMapper.transform(photos.find(_.id == event.mainPhotoId).get),
      photos.filter(_.id != event.mainPhotoId).map(PhotoDescriptorDataMapper.transform),
      artists.map { a =>
        ArtistDescriptor(a.id, a.name, a.email, photos.filter(p => a.photos.map(_.photoId).contains(p.id)))
      }
    )
  }
}
