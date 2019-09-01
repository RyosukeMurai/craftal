package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.artist.ArtistDescriptor
import net.craftal.web.model.attribute.AttributeDescriptor
import net.craftal.web.model.event.{EventDetail, EventScheduleDescriptor}
import net.craftal.web.translator.common.DateTimeTranslator
import net.craftal.web.translator.event.EventLocationTranslator
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}


object EventDetailDataMapper extends DataMapper {
  def transform(event: Event, photos: List[Photo], prefectures: List[Prefecture], artists: List[Artist], attributes: List[Attribute])
               (implicit request: Request[AnyContent], messages: Messages): EventDetail = {
    EventDetail(
      event.id,
      event.title,
      event.description,
      event.homePageUrl,
      event.facebookUrl,
      event.twitterUrl,
      event.instagramUrl,
      event.status,
      event.schedule.map(s =>
        EventScheduleDescriptor(
          prefectures.find(_.id == s.prefectureId).get.name,
          EventLocationTranslator.translate(event.location),
          DateTimeTranslator.translate(s.startTime, s.endTime),
          s.venue,
          s.address,
          s.postalCode,
          s.howToAccess,
          s.venueUrl,
          s.venueRemarks
        )
      ).toList,
      PhotoDescriptorDataMapper.transform(photos.find(_.id == event.mainPhotoId).get),
      photos.filter(p => event.subPhotoIdList.contains(p.id)).map(PhotoDescriptorDataMapper.transform),
      artists.map { a =>
        ArtistDescriptor(
          a.id,
          a.name,
          a.email,
          prefectures.find(_.id == a.prefectureId).get.name,
          PhotoDescriptorDataMapper.transform(photos.find(_.id == a.iconPhotoId).get)
        )
      },
      attributes.map { a =>
        AttributeDescriptor(
          a.name
        )
      }
    )
  }
}
