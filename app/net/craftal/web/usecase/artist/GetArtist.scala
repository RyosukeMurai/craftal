package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.core.domain.model.attribute.Attribute
import net.craftal.core.domain.model.event.Event
import net.craftal.core.domain.model.genre.Genre
import net.craftal.core.domain.model.photo.Photo
import net.craftal.core.domain.model.prefecture.Prefecture
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

class GetArtist @Inject()(domainService: DomainService)(implicit ex: ExecutionContext) extends Interactor {

  def execute(artistId: Int)
             (implicit request: Request[AnyContent], messages: Messages):
  Future[(Artist, Genre, List[Prefecture], List[Photo], List[Event], List[Attribute])] =
    for {
      a <- this.domainService.getArtist(artistId)
      g <- this.domainService.getGenre(a.genreId)
      ps <- this.domainService.getPhotos(a.photos.map(_.photoId))
      e <- this.domainService.getEventsForArtistParticipating(artistId)
      p <- this.domainService.getPrefectures(e.flatMap(_.schedule.map(_.prefectureId)) :+ a.prefectureId)
      at <- this.domainService.getAttributes(a.attributes.map(_.attributeId))
    } yield (a, g, p, ps, e, at)
}
