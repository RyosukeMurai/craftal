package web.controller.artist

import javax.inject._
import controllers.AssetsFinder
import net.craftal.core.usecase.artist.GetArtistsOfGenre
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import usecase.photo.GetPhotosByIdList
import web.action.{ActionWithNavigation, NavigationContext}
import web.form.artist.SearchArtistForm
import web.mapper.ArtistListDataMapper

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ListArtistByGenreController @Inject()(controllerComponents: ControllerComponents,
                                            actionWithNavigation: ActionWithNavigation,
                                            getArtists: GetArtistsOfGenre,
                                            getPhotos: GetPhotosByIdList)
                                           (implicit executionContext: ExecutionContext,
                                            webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(genreId: Int): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[_] =>
    SearchArtistForm.form.bindFromRequest(request.queryString).fold(
      _ => Future.successful(BadRequest(web.view.artist.html.list(None))),
      data => for {
        e <- getArtists.execute(genreId, Option(data.keyword))
        p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
      } yield {
        Ok(
          web.view.artist.html.list(
            Option(ArtistListDataMapper.transform(e, p))
          )
        )
      })
  }
}
