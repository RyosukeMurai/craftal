package net.craftal.web.translator.common

import net.craftal.web.translator.Translator
import org.joda.time.DateTime
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

object DateTimeTranslator extends Translator {

  def translate(termStart: DateTime, termEnd: DateTime)
               (implicit request: Request[AnyContent], messages: Messages): String =
    s"${termStart.toString("HH:mm")} ~ ${termEnd.toString("HH:mm")}"

  def translate(dateTime: DateTime, pattern: String): String = dateTime.toString(pattern)
}
