package net.craftal.web.usecase.event

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.domain.model.event.{Event, EventRepository}
import org.joda.time.DateTime

import scala.concurrent.Future

@Singleton
class GetEvents @Inject()(repository: EventRepository) extends Interactor {

  def execute(): Future[List[Event]] = this.repository.findAll()

  def execute(termStart: DateTime,
              termEnd: Option[DateTime],
              keyword: Option[String]): Future[List[Event]] =
    this.repository.findEventsByTerm(termStart, termEnd, keyword)
}
