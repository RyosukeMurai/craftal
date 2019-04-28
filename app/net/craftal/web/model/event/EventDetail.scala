package net.craftal.web.model.event

import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.web.model.artist.ArtistDescriptor
import net.craftal.web.model.photo.PhotoDescriptor

case class EventDetail(id: Int,
                       title: String,
                       description: String,
                       status: EventStatus,
                       schedule: List[EventScheduleDescriptor],
                       eyecatchPhoto: PhotoDescriptor,
                       subPhotos: List[PhotoDescriptor],
                       artists: List[ArtistDescriptor])
