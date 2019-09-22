package net.craftal.web.mapper

import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.event.{EventCalendar, EventDescriptor, EventScheduleDescriptor}
import net.craftal.web.translator.common.DateTimeTranslator
import net.craftal.web.translator.event.EventLocationTranslator
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}


object EventCalendarDataMapper extends DataMapper {

  def transform(events: List[Event],
                photos: List[Photo],
                prefectures: List[Prefecture],
                genres: List[Genre])
               (implicit request: Request[AnyContent], messages: Messages): EventCalendar =
    EventCalendar(events
      .flatMap(e => e.schedule.map(s => (s, e)))
      .groupBy(_._1.startTime)
      .map(e => DateTimeTranslator.translate(e._1, "M/d") ->
        e._2.map(r =>
          EventDescriptor(
            r._2.id,
            r._2.title,
            r._2.description,
            r._2.status,
            EventScheduleDescriptor(
              prefectures.find(_.id == r._1.prefectureId).get.name,
              EventLocationTranslator.translate(r._2.location),
              DateTimeTranslator.translate(r._1.startTime, r._1.endTime),
              r._1.venue,
              r._1.address,
              r._1.postalCode,
              r._1.howToAccess,
              r._1.venueUrl,
              r._1.venueRemarks
            ),
            PhotoDescriptorDataMapper.transform(photos.find(_.id == r._2.mainPhotoId).get),
            r._2.homePageUrl
          )
        )
      ),
      genres.map(g => g.id.toString -> g.name)
    )
}
