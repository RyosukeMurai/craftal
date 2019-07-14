package net.craftal.web.usecase.artist

import javax.inject._
import net.craftal.common.usecase.Interactor
import net.craftal.core.api.DomainService
import net.craftal.core.domain.model.artist.Artist
import net.craftal.identityaccess.domain.model.role.RoleCode
import net.craftal.web.usecase.auth.Register
import play.api.i18n.Messages
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class RegisterArtist @Inject()(domainService: DomainService,
                               register: Register)
                              (implicit ex: ExecutionContext) extends Interactor {

  def execute(email: String,
              name: String,
              password: String,
              genreId: Int,
              verificationExpiration: FiniteDuration = 5 minutes)
             (implicit request: Request[AnyContent], messages: Messages): Future[Artist] = {
    this.register
      .execute(email, name, password, RoleCode.artist.toString, verificationExpiration)
      .flatMap(u => {
        this.domainService.registerArtist(userId = u.id, genreId = genreId)
      })
  }
}
