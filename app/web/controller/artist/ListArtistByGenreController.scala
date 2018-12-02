package web.controller.artist

import javax.inject._

import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import useCase.artist.{GetArtists, GetArtistsByGenre}
import useCase.photo.GetPhotosByIdList
import web.action.ActionWithNavigation
import web.mapper.ArtistSummaryDataMapper
import web.model.form.artist.ListArtistForm

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ListArtistByGenreController @Inject()(controllerComponents: ControllerComponents,
                                            getArtists: GetArtistsByGenre,
                                            getPhotos: GetPhotosByIdList)
                                           (implicit executionContext: ExecutionContext,
                                            webJarsUtil: WebJarsUtil,
                                            assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(genreId: Int): Action[AnyContent] = ActionWithNavigation {
    Action.async { implicit request =>
      ListArtistForm.form.bindFromRequest(request.queryString).fold(
        form => Future.successful(BadRequest(web.view.artist.html.list(form, None))),
        data => for {
          e <- getArtists.execute(genreId, Option(data.keyword))
          p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
        } yield {
          Ok(
            web.view.artist.html.list(
              ListArtistForm.form,
              Option(ArtistSummaryDataMapper.transform(e, p))
            )
          )
        })
    }
  }
}