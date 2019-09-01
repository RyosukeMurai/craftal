package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.event._
import org.joda.time.DateTime

object EventEntityDataMapper {

  def transform(eventEntity: Tables.EventRow): Event =
    Event(
      id = eventEntity.id,
      title = eventEntity.title,
      description = eventEntity.description,
      status = EventStatus.apply(eventEntity.statusId),
      location = EventLocation.apply(eventEntity.locationId),
      homePageUrl = eventEntity.homePageUrl,
      facebookUrl = eventEntity.facebookUrl,
      twitterUrl = eventEntity.twitterUrl,
      instagramUrl = eventEntity.instagramUrl
    )

  def transform(eventEntity: Tables.EventRow,
                eventScheduleCollection: List[Tables.EventScheduleRow],
                eventPhotoCollection: List[Tables.EventPhotoRow],
                eventAttributeCollection: List[Tables.EventAttributeRow]): Event =
    Event(
      id = eventEntity.id,
      title = eventEntity.title,
      description = eventEntity.description,
      status = EventStatus.apply(eventEntity.statusId),
      location = EventLocation.apply(eventEntity.locationId),
      homePageUrl = eventEntity.homePageUrl,
      facebookUrl = eventEntity.facebookUrl,
      twitterUrl = eventEntity.twitterUrl,
      instagramUrl = eventEntity.instagramUrl,
      eventScheduleCollection.map(schedule => EventSchedule(
        mapCoordinate = EventMapCoordinate(latitude = 0, longitude = 0),
        venue = schedule.venue,
        startTime = new DateTime(schedule.startTime.getTime),
        endTime = new DateTime(schedule.endTime.getTime),
        prefectureId = schedule.prefectureId,
        address = schedule.address,
        postalCode = schedule.postalCode,
        howToAccess = schedule.howToAccess,
        venueUrl = schedule.venueUrl,
        venueRemarks = schedule.venueRemarks
      )),
      eventPhotoCollection.map(photo => EventPhoto(
        photoId = photo.photoId,
        positionNo = photo.positionNo
      )),
      eventAttributeCollection.map(attribute => EventAttribute(
        attributeId = attribute.attributeId
      ))
    )


  def transformCollection(eventRows: List[(Tables.EventRow, Tables.EventScheduleRow, Tables.EventPhotoRow, Tables.EventAttributeRow)]): List[Event] =
    eventRows
      .groupBy(_._1.id)
      .map(m => this.transform(
        m._2.head._1,
        m._2.map(_._2).filter(_.eventId == m._1).groupBy(_.id).map(_._2.head).toList,
        m._2.map(_._3).filter(_.eventId == m._1).groupBy(_.id).map(_._2.head).toList,
        m._2.map(_._4).filter(_.eventId == m._1).groupBy(_.id).map(_._2.head).toList
      ))
      .toList

}
