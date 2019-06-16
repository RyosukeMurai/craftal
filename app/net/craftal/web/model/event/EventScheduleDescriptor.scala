package net.craftal.web.model.event

case class EventScheduleDescriptor(prefecture: String,
                                   location: String,
                                   schedule: String,
                                   venue: String,
                                   address: String,
                                   postalCode: String,
                                   howToAccess: String,
                                   venueUrl: Option[String],
                                   venueRemarks: String)
