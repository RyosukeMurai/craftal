package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.genre.Genre

import scala.concurrent.Future

@Singleton
class GetArtists @Inject()(domainService: DomainService) extends Interactor {

  case class Item(id: Int,
                  name: String,
                  email: String,
                  genre: Genre)

  case class Response(items: Seq[Item])

  def execute(keyword: Option[String]): Future[Response] = {
    for {
      a <- this.domainService.getArtists(keyword = keyword)
      g <- this.domainService.getGenres(a.map(_.genreId))
    } yield Response(a.id, a.name, a.email, g)
  }
}
