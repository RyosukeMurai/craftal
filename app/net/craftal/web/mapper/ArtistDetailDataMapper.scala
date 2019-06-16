package net.craftal.web.mapper

import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.artist.ArtistDetail
import net.craftal.web.model.attribute.AttributeDescriptor
import net.craftal.web.model.event.{EventDescriptor, EventScheduleDescriptor}
import net.craftal.web.translator.common.DateTimeTranslator
import net.craftal.web.translator.event.EventLocationTranslator
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}


object ArtistDetailDataMapper extends DataMapper {
  def transform(artist: Artist,
                genre: Genre,
                prefectures: Seq[Prefecture],
                photos: Seq[Photo],
                events: Seq[Event],
                attributes: Seq[Attribute])
               (implicit request: Request[AnyContent], messages: Messages): ArtistDetail =
    ArtistDetail(
      artist.id,
      artist.name,
      artist.email,
      genre.name,
      prefectures.find(_.id == artist.prefectureId).get.name,
      artist.aboutInquiry,
      artist.homePageUrl,
      artist.shopPageUrl,
      artist.twitterUrl,
      artist.facebookUrl,
      artist.instagramUrl,
      artist.selfIntroduction,
      PhotoDescriptorDataMapper.transform(photos.find(_.id == artist.coverPhotoId).get),
      PhotoDescriptorDataMapper.transform(photos.find(_.id == artist.iconPhotoId).get),
      photos
        .filter(p => !(List(artist.coverPhotoId, artist.iconPhotoId) contains p.id))
        .map(PhotoDescriptorDataMapper.transform).toList,
      events
        .map(e => EventDescriptor(
          e.id,
          e.title,
          e.description,
          e.status,
          EventScheduleDescriptor(
            prefectures.find(_.id == e.schedule.head.prefectureId).get.name,
            EventLocationTranslator.translate(e.location),
            DateTimeTranslator.translate(e.schedule.head.startTime, e.schedule.head.endTime),
            e.schedule.head.venue,
            e.schedule.head.address,
            e.schedule.head.postalCode,
            e.schedule.head.howToAccess,
            e.schedule.head.venueUrl,
            e.schedule.head.venueRemarks
          ),
          PhotoDescriptorDataMapper.transform(photos.find(_.id == e.mainPhotoId).get),
          e.homePageUrl
        )),
      attributes.map(a => AttributeDescriptor(a.name)).toList
    )
}
