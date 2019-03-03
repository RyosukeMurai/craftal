package web.controller.artist

import javax.inject._

import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.mvc._
import usecase.artist.GetArtists
import usecase.photo.GetPhotosByIdList
import web.action.{ActionWithNavigation, NavigationContext}
import web.form.artist.SearchArtistForm
import web.mapper.ArtistSummaryDataMapper

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SummaryArtistController @Inject()(controllerComponents: ControllerComponents,
                                        actionWithNavigation: ActionWithNavigation,
                                        getArtists: GetArtists,
                                        getPhotos: GetPhotosByIdList)
                                       (implicit executionContext: ExecutionContext,
                                        webJarsUtil: WebJarsUtil,
                                        assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[_] =>
    SearchArtistForm.form.bindFromRequest(request.queryString).fold(
      form => Future.successful(BadRequest(web.view.artist.html.summary(form, None))),
      data => for {
        e <- getArtists.execute(Option(data.keyword))
        p <- getPhotos.execute(e.flatMap(_.getPhotos.map(_.photoId)))
      } yield {
        Ok(
          web.view.artist.html.summary(
            SearchArtistForm.form,
            Option(ArtistSummaryDataMapper.transform(e, p))
          )
        )
      })
  }
}
