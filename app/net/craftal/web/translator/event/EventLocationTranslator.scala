package net.craftal.web.translator.event

import net.craftal.core.domain.model.event.EventLocation
import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.web.translator.Translator
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

object EventLocationTranslator extends Translator {

  def translate(location: EventLocation)
               (implicit request: Request[AnyContent], messages: Messages): String =
    location match {
      case EventLocation.indoor => messages("craftal.core.event.location.indoor")
      case EventLocation.outdoor => messages("craftal.core.event.location.outdoor")
    }
}
