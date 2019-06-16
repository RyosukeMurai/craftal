package net.craftal.web.model.event

import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.web.model.photo.PhotoDescriptor

case class EventDescriptor(id: Int,
                           title: String,
                           description: String,
                           status: EventStatus,
                           schedule: EventScheduleDescriptor,
                           eyecatchPhoto: PhotoDescriptor,
                           homePageUrl: Option[String])
