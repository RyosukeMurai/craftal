package domain.event.interactor

import java.util.Date

import domain.event.{Event, EventRepository}
import domain.shared.Interactor
import javax.inject._
import presentation.model.event.EventSearchCondition

import scala.concurrent.Future

@Singleton
class GetEventsWithinPeriod @Inject()(repository: EventRepository) extends Interactor {

  def execute(termStart: Date, termEnd: Option[Date], findCondition: Option[EventSearchCondition]): Future[List[Event]] =
    this.repository.findByTerm(termStart, termEnd, findCondition.map(condition => condition.keyword))
}
