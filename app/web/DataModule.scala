package web

import com.google.inject.AbstractModule
import domain.model.auth.AuthRepository
import domain.model.genre.GenreRepository
import domain.model.user.UserRepository
import net.craftal.core.domain.model.artist.ArtistRepository
import net.craftal.core.domain.model.event.EventRepository
import net.craftal.core.domain.model.photo.PhotoRepository
import net.craftal.core.domain.repository.EventRepository

class DataModule extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[UserRepository]).to(classOf[UserDataRepository])

    bind(classOf[AuthRepository]).to(classOf[AuthDataStore])

    bind(classOf[EventRepository]).to(classOf[EventDataStore])

    bind(classOf[ArtistRepository]).to(classOf[ArtistDataStore])

    bind(classOf[PhotoRepository]).to(classOf[PhotoDataStore])

    bind(classOf[GenreRepository]).to(classOf[GenreDataStore])
  }

}
