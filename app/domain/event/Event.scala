package domain.event

import domain.event.EventLocation.EventLocation
import domain.event.EventStatus.EventStatus
import domain.shared.Entity

class Event(val id: Int,
            var title: String,
            var description: String,
            var status: EventStatus,
            var location: EventLocation,
            protected var schedule: List[EventSchedule] = List(),
            protected var photos: List[EventPhoto] = List()) extends Entity[Event] {

  def getSchedule: List[EventSchedule] = schedule.toList //immutable

  def setSchedule(schedule: List[EventSchedule]): Unit = this.schedule = schedule

  def getPhotos: List[EventPhoto] = photos.toList //immutable

  def setPhotos(photos: List[EventPhoto]): Unit = this.photos = photos

  def mainPhotoId: Int = {
    this.photos match {
      case x :: _ =>
      case _ => throw new IllegalAccessException(s"Event ($this.title) doesn't have any photos.")
    }
    this.photos.sortWith(_.positionNo < _.positionNo).head.photoId
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Event]

  override def equals(other: Any): Boolean = other match {
    case that: Event =>
      (that canEqual this) &&
        id == that.id
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(id)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
