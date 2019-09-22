package net.craftal.web.model.event

case class EventCalendar(events: Map[String, Seq[EventDescriptor]],
                         genreSearchConditions:Seq[(String,String)])
