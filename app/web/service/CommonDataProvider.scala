package web.service

import javax.inject.Inject

import play.api.i18n.{Lang, Langs, MessagesApi}
import web.model.common.Navigation

class CommonDataProvider @Inject()(langs: Langs, messagesApi: MessagesApi) {
  val lang: Lang = langs.availables.head

  val navigationCollectionForPublicAccess: Seq[Navigation] = {
    Seq(
      Navigation(
        messagesApi("craftal.public.common.nav.auth.sign-in")(lang),
        web.controller.auth.routes.SignInController.view()
      ),
      Navigation(
        messagesApi("craftal.public.common.nav.event.list-event-calendar")(lang),
        web.controller.event.routes.ListEventCalendarController.view()
      ),
      Navigation(
        messagesApi("craftal.public.common.nav.artist.summary-artist")(lang),
        web.controller.artist.routes.SummaryArtistController.view()
      )
    )
  }
}