package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.artist.ArtistDescriptor
import net.craftal.web.model.event.{EventDescriptor, EventScheduleDescriptor}
import net.craftal.web.model.mypage.Mypage
import net.craftal.web.translator.common.DateTimeTranslator
import net.craftal.web.translator.event.EventLocationTranslator
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}


object MypageDataMapper extends DataMapper {

  def transform(artists: List[Artist],
                events: List[Event],
                photos: List[Photo],
                prefectures: List[Prefecture])
               (implicit request: Request[AnyContent], messages: Messages): Mypage =
    Mypage(
      artists.map { a =>
        ArtistDescriptor(
          a.id,
          a.name,
          a.email,
          prefectures.find(_.id == a.prefectureId).get.name,
          PhotoDescriptorDataMapper.transform(photos.find(_.id == a.iconPhotoId).get)
        )
      },
      events.map { e =>
        EventDescriptor(
          e.id,
          e.title,
          e.description,
          e.status,
          e.schedule.map { s =>
            EventScheduleDescriptor(
              prefectures.find(_.id == s.prefectureId).get.name,
              EventLocationTranslator.translate(e.location),
              DateTimeTranslator.translate(s.startTime, s.endTime),
              s.venue,
              s.address,
              s.postalCode,
              s.howToAccess,
              s.venueUrl,
              s.venueRemarks
            )
          }.head,
          PhotoDescriptorDataMapper.transform(photos.find(_.id == e.mainPhotoId).get),
          e.homePageUrl
        )
      }
    )
}
