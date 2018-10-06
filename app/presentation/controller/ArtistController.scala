package presentation.controller

import controllers.AssetsFinder
import domain.artist.interactor.{GetArtist, GetArtists, GetArtistsByGenre}
import domain.photo.interactor.GetPhotosByIdList
import javax.inject._
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
                                 getPhotos: GetPhotosByIdList)
                                (implicit executionContext: ExecutionContext, assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index: Action[AnyContent] = Action.async { implicit request =>
    val searchForm = Form(mapping(
      "keyword" -> text
    )(ArtistSearchCondition.apply)(ArtistSearchCondition.unapply))
    val condition = searchForm.bindFromRequest(request.queryString).value
    print(condition)
    for {
      e <- getArtists.execute(condition)
      p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
    } yield {
      Ok(
        presentation.view.artist.html.index(
          ArtistSummaryDataMapper.transform(e, p),
          searchForm
        )
      )
    }
  }

  def detail(id: String): Action[AnyContent] = Action.async {
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
      e <- getArtistsByGenre.execute(genreId.toInt, condition)
      p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
    } yield {
      Ok(
        presentation.view.artist.html.index(
          ArtistSummaryDataMapper.transform(e, p),
          searchForm
        )
      )
    }
  }
}
