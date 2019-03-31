package net.craftal.core.api

import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.usecase.artist._
import net.craftal.core.usecase.event._
import net.craftal.core.usecase.genre._
import net.craftal.core.usecase.photo._
import org.joda.time.DateTime

import scala.concurrent.{ExecutionContext, Future}

//TODO(RyosukeMurai): Separate API
class DomainService @Inject()(getArtist: GetArtist,
                              getArtists: GetArtists,
                              getArtistsOfGenre: GetArtistsOfGenre,
                              getArtistsParticipatingInEvent: GetArtistsParticipatingInEvent,
                              getEvent: GetEvent,
                              getEventsInteractor: GetEvents,
                              createEvent: CreateEvent,
                              countNumberOfEventsInteractor: CountNumberOfEvents,
                              getGenreInteractor: GetGenre,
                              getGenresInteractor: GetGenres,
                              getPhoto: GetPhoto,
                              getPhotosInteractor: GetPhotos)
                             (implicit ex: ExecutionContext) {

  def getArtist(artistId: Int): Future[Artist] = this.getArtist.execute(artistId)

  def getArtists(keyword: Option[String]): Future[List[Artist]] =
    this.getArtists.execute(keyword)

  def getArtistsOfGenre(genreId: Int, keyword: Option[String]): Future[List[Artist]] =
    this.getArtistsOfGenre.execute(genreId, keyword)

  def getArtistsParticipatingInEvent(eventId: Int, keyword: Option[String]): Future[List[Artist]] =
    this.getArtistsParticipatingInEvent.execute(eventId, keyword)

  def getEvent(eventId: Int): Future[Event] = this.getEvent.execute(eventId)

  def getEvents: Future[List[Event]] = this.getEventsInteractor.execute()

  def getEvents(termStart: DateTime,
                termEnd: Option[DateTime],
                keyword: Option[String]): Future[List[Event]] =
    this.getEventsInteractor.execute(termStart, termEnd, keyword)

  def createEvent(title: String,
                  description: String,
                  status: EventStatus,
                  location: EventLocation): Future[Event] =
    this.createEvent.execute(
      title = title,
      description = description,
      status = status,
      location = location
    )

  def countNumberOfEvents: Future[Int] = this.countNumberOfEventsInteractor.execute()

  def getGenre(genreId: Int): Future[Genre] = this.getGenreInteractor.execute(genreId)

  def getGenres: Future[List[Genre]] = this.getGenresInteractor.execute()

  def getGenres(genreIdList: List[Int]): Future[List[Genre]] = this.getGenresInteractor.execute()

  def getPhoto(photoId: Int): Future[Photo] = this.getPhoto.execute(photoId)

  def getPhotos(photoIdList: List[Int]): Future[List[Photo]] = this.getPhotosInteractor.execute(photoIdList)
}