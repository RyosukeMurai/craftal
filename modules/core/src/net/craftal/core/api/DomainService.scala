package net.craftal.core.api

import javax.inject.Inject
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.event.EventLocation.EventLocation
import net.craftal.core.domain.model.event.EventStatus.EventStatus
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.member.Member
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.core.usecase.artist._
import net.craftal.core.usecase.attribute.{GetAttribute, GetAttributes}
import net.craftal.core.usecase.event._
import net.craftal.core.usecase.genre._
import net.craftal.core.usecase.member.{GetFollowingArtists, GetFollowingEvents, GetMember, UpdateMemberProfile}
import net.craftal.core.usecase.photo._
import net.craftal.core.usecase.prefecture.{GetPrefecture, GetPrefectures}
import org.joda.time.DateTime

import scala.concurrent.{ExecutionContext, Future}

//TODO(RyosukeMurai): Separate API
class DomainService @Inject()(getMember: GetMember,
                              updateMemberProfile: UpdateMemberProfile,
                              getArtist: GetArtist,
                              getArtists: GetArtists,
                              getArtistsOfGenre: GetArtistsOfGenre,
                              getArtistsParticipatingInEvent: GetArtistsParticipatingInEvent,
                              getFollowingArtists: GetFollowingArtists,
                              getEvent: GetEvent,
                              getEventsInteractor: GetEvents,
                              getEventsForArtistParticipating: GetEventsForArtistParticipating,
                              getFollowingEvents: GetFollowingEvents,
                              createEvent: CreateEvent,
                              countNumberOfEventsInteractor: CountNumberOfEvents,
                              getGenreInteractor: GetGenre,
                              getGenresInteractor: GetGenres,
                              getPhoto: GetPhoto,
                              getPhotosInteractor: GetPhotos,
                              getPrefectureInteractor: GetPrefecture,
                              getPrefecturesInteractor: GetPrefectures,
                              getAttribute: GetAttribute,
                              getAttributesInteractor: GetAttributes)
                             (implicit ex: ExecutionContext) {

  def getMember(memberId: Int): Future[Member] = this.getMember.execute(memberId)

  def updateMemberProfile(memberId: Int,
                          name: Option[String],
                          prefectureId: Option[Int]): Future[Member] =
    this.updateMemberProfile.execute(
      memberId,
      name,
      prefectureId
    )

  def getArtist(artistId: Int): Future[Artist] = this.getArtist.execute(artistId)

  def getArtists(keyword: Option[String]): Future[List[Artist]] =
    this.getArtists.execute(keyword)

  def getFollowingArtists(followerId: Int): Future[List[Artist]] =
    this.getFollowingArtists.execute(followerId)

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

  def getEventsForArtistParticipating(artistId: Int): Future[List[Event]] = this.getEventsForArtistParticipating.execute(artistId)

  def getFollowingEvents(followerId: Int): Future[List[Event]] =
    this.getFollowingEvents.execute(followerId)

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

  def getPrefecture(prefectureId: Int): Future[Prefecture] = this.getPrefectureInteractor.execute(prefectureId)

  def getPrefectures: Future[List[Prefecture]] = this.getPrefecturesInteractor.execute

  def getPrefectures(prefectureIdList: List[Int]): Future[List[Prefecture]] = this.getPrefecturesInteractor.execute(prefectureIdList)

  def getAttribute(attributeId: Int): Future[Attribute] = this.getAttribute.execute(attributeId)

  def getAttributes(attributeIdList: List[Int]): Future[List[Attribute]] = this.getAttributesInteractor.execute(attributeIdList)

}
