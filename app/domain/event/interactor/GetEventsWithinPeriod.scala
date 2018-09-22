package domain.event.interactor

import java.util.Date

import domain.event.{Event, EventRepository}
import domain.shared.Interactor
import javax.inject._

import scala.concurrent.Future

@Singleton
class GetEventsWithinPeriod @Inject()(repository: EventRepository) extends Interactor {

  def execute(startDate: Date, endDate: Date = null): Future[List[Event]] =
    this.repository.findByPeriod(startDate, endDate)
}
