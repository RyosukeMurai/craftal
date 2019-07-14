package net.craftal.core.domain.model.artist

import scala.concurrent.Future

trait ArtistRepository {

  def findArtist(artistId: Int): Future[Artist]

  def findArtistsByKeyword(keyword: Option[String]): Future[List[Artist]]

  def findArtistsByEventId(eventId: Int, keyword: Option[String]): Future[List[Artist]]

  def findArtistsByGenreId(genreId: Int, keyword: Option[String]): Future[List[Artist]]

  def findArtistsByFollowerId(followerId: Int): Future[List[Artist]]

  def findAll(): Future[List[Artist]]

  def createArtist(userId: Int, genreId: Int): Future[Artist]
}
