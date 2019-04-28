package net.craftal.web.mapper

import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.event.{EventCalendar, EventDescriptor, EventScheduleDescriptor}
import net.craftal.web.translator.common.DateTimeTranslator
import net.craftal.web.translator.event.EventLocationTranslator
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}


object EventCalendarDataMapper extends DataMapper {

  def transform(eventCollection: List[Event],
                photoCollection: List[Photo],
                prefectureCollection: List[Prefecture])
               (implicit request: Request[AnyContent], messages: Messages): EventCalendar =
    EventCalendar(eventCollection
      .flatMap(e => e.schedule.map(s => (s, e)))
      .groupBy(_._1.startTime)
      .map(e => DateTimeTranslator.translate(e._1, "MM月dd日") ->
        e._2.map(r =>
          EventDescriptor(
            r._2.id,
            r._2.title,
            r._2.description,
            r._2.status,
            EventScheduleDescriptor(
              prefectureCollection.find(_.id == r._1.prefectureId).get.name,
              EventLocationTranslator.translate(r._2.location),
              DateTimeTranslator.translate(r._1.startTime, r._1.endTime),
              r._1.venue
            ),
            PhotoDescriptorDataMapper.transform(photoCollection.find(_.id == r._2.mainPhotoId).get)
          )
        )
      )
    )
}
