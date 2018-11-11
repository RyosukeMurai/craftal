package web

import com.google.inject.AbstractModule
import data.store._
import domain.model.auth.AuthRepository
import domain.model.artist.ArtistRepository
import domain.model.event.EventRepository
import domain.model.genre.GenreRepository
import domain.model.photo.PhotoRepository
import domain.model.user.UserRepository

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
