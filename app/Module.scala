
import java.time.Clock

import com.google.inject.AbstractModule
import data.repository._
import domain.model.artist.ArtistRepository
import domain.model.event.EventRepository
import domain.model.genre.GenreRepository
import domain.model.photo.PhotoRepository
import domain.model.user.UserRepository

/**
  * This class is a Guice module that tells Guice how to bind several
  * different types. This Guice module is created when the Play
  * application starts.
  *
  * Play will automatically use any class called `Module` that is in
  * the root package. You can create modules in other locations by
  * adding `play.modules.enabled` settings to the `application.conf`
  * configuration file.
  */
class Module extends AbstractModule {

  override def configure(): Unit = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.

    bind(classOf[UserRepository]).to(classOf[UserDataRepository])

    bind(classOf[EventRepository]).to(classOf[EventDataRepository])

    bind(classOf[ArtistRepository]).to(classOf[ArtistDataRepository])

    bind(classOf[PhotoRepository]).to(classOf[PhotoDataRepository])

    bind(classOf[GenreRepository]).to(classOf[GenreDataRepository])
  }

}
