package net.craftal.web.controller.mypage

import com.mohiva.play.silhouette.api.Silhouette
import controllers.AssetsFinder
import javax.inject._
import net.craftal.web.port.silhouette.DefaultEnv
import net.craftal.web.presenter.mypage.IndexViewPresenter
import org.webjars.play.WebJarsUtil
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class IndexController @Inject()(controllerComponents: ControllerComponents,
                                silhouette: Silhouette[DefaultEnv],
                                presenter: IndexViewPresenter)
                               (implicit executionContext: ExecutionContext,
                                webJarsUtil: WebJarsUtil,
                                assetsFinder: AssetsFinder)
  extends AbstractController(controllerComponents) with play.api.i18n.I18nSupport {

  def view: Action[AnyContent] = silhouette.SecuredAction.async { implicit request =>
    Future.successful(Ok(presenter.present))
  }
}
