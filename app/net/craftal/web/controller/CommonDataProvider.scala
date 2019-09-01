package net.craftal.web.controller

import javax.inject.Inject
import net.craftal.web.model.common.Navigation
import play.api.i18n.{Lang, Langs, MessagesApi}

class CommonDataProvider @Inject()(langs: Langs, messagesApi: MessagesApi) {
  val lang: Lang = langs.availables.head

  val navigationCollectionForPublicAccess: Seq[Navigation] = {
    Seq(
      Navigation(
        messagesApi("craftal.public.common.nav.auth.sign-in")(lang),
        net.craftal.web.controller.auth.routes.SignInController.view()
      ),
      Navigation(
        messagesApi("craftal.public.common.nav.event.list-event-calendar")(lang),
        net.craftal.web.controller.event.routes.ListEventCalendarController.view()
      )
    )
  }
}