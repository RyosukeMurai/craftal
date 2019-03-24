package net.craftal.web.controller.artist

import controllers.AssetsFinder
import javax.inject._
import net.craftal.core.usecase.artist.GetArtists
import net.craftal.core.usecase.photo.GetPhotos
import net.craftal.web.controller.{ActionWithNavigation, NavigationContext}
import net.craftal.web.mapper.ArtistSummaryDataMapper
import net.craftal.web.model.form.artist.SearchArtistForm
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SummaryArtistController @Inject()(controllerComponents: ControllerComponents,
                                        actionWithNavigation: ActionWithNavigation,
                                        getArtists: GetArtists,
                                        getPhotos: GetPhotos)
                                       (implicit executionContext: ExecutionContext,
                                        webJarsUtil: WebJarsUtil,
                                        assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view(): Action[AnyContent] = actionWithNavigation.async { implicit request: NavigationContext[_] =>
    SearchArtistForm.form.bindFromRequest(request.queryString).fold(
      form => Future.successful(BadRequest(web.view.artist.html.summary(form, None))),
      data => for {
        e <- getArtists.execute(Option(data.keyword))
        p <- getPhotos.execute(e.flatMap(_.photos.map(_.photoId)))
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
