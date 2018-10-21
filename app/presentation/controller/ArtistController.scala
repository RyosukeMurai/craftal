package presentation.controller

import controllers.AssetsFinder
import domain.artist.interactor.{GetArtist, GetArtists, GetArtistsByGenre}
import domain.genre.interactor.GetGenres
import domain.photo.interactor.GetPhotosByIdList
import javax.inject._
import org.webjars.play.WebJarsUtil
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import presentation.mapper.{ArtistDetailDataMapper, ArtistSummaryDataMapper}
import presentation.model.artist.ArtistSearchCondition

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class ArtistController @Inject()(controllerComponents: ControllerComponents,
                                 getArtist: GetArtist,
                                 getArtists: GetArtists,
                                 getArtistsByGenre: GetArtistsByGenre,
                                 getPhotos: GetPhotosByIdList,
                                 getGenres: GetGenres)
                                (implicit executionContext: ExecutionContext, webJarsUtil: WebJarsUtil, assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def index: Action[AnyContent] = Action.async { implicit request =>
    val searchForm = Form(mapping(
      "keyword" -> text
    )(ArtistSearchCondition.apply)(ArtistSearchCondition.unapply))
    val condition = searchForm.bindFromRequest(request.queryString).value
    print(condition)
    for {
      g <- getGenres.execute()
      e <- getArtists.execute(condition)
      p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
    } yield {
      Ok(
        presentation.view.artist.html.index(
          ArtistSummaryDataMapper.transform(e, p),
          g,
          searchForm
        )
      )
    }
  }

  def detail(id: String): Action[AnyContent] = Action.async { implicit request =>
    for {
      v <- getArtist.execute(id.toInt)
    } yield {
      Ok(
        presentation.view.artist.html.detail(
          ArtistDetailDataMapper.transform(v, List())
        )
      )
    }
  }

  def list(genreId: String): Action[AnyContent] = Action.async { implicit request =>
    val searchForm = Form(mapping(
      "keyword" -> text
    )(ArtistSearchCondition.apply)(ArtistSearchCondition.unapply))
    val condition = searchForm.bindFromRequest(request.queryString).value
    for {
      g <- getGenres.execute()
      e <- getArtistsByGenre.execute(genreId.toInt, condition)
      p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
    } yield {
      Ok(
        presentation.view.artist.html.index(
          ArtistSummaryDataMapper.transform(e, p),
          g,
          searchForm
        )
      )
    }
  }
}
