package useCase.event

import java.util.Date

import domain.model.event.{Event, EventRepository}
import javax.inject._
import useCase.Interactor
import web.model.event.EventSearchCondition

import scala.concurrent.Future

@Singleton
class GetEventsWithinPeriod @Inject()(repository: EventRepository) extends Interactor {

  def execute(termStart: Date, termEnd: Option[Date], findCondition: Option[EventSearchCondition]): Future[List[Event]] =
    this.repository.findByTerm(termStart, termEnd, findCondition.map(condition => condition.keyword))
}
