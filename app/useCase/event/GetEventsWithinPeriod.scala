package useCase.event

import java.util.Date
import javax.inject._

import domain.model.event.{Event, EventRepository}
import useCase.Interactor

import scala.concurrent.Future

@Singleton
class GetEventsWithinPeriod @Inject()(repository: EventRepository) extends Interactor {

  def execute(termStart: Date, termEnd: Option[Date], keyword: Option[String]): Future[List[Event]] =
    this.repository.findByTerm(termStart, termEnd, keyword)
}
