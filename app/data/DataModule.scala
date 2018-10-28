package data

import com.google.inject.AbstractModule
import data.repository._
import domain.model.artist.ArtistRepository
import domain.model.event.EventRepository
import domain.model.genre.GenreRepository
import domain.model.photo.PhotoRepository
import domain.model.user.UserRepository

class DataModule extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[UserRepository]).to(classOf[UserDataRepository])

    bind(classOf[EventRepository]).to(classOf[EventDataRepository])

    bind(classOf[ArtistRepository]).to(classOf[ArtistDataRepository])

    bind(classOf[PhotoRepository]).to(classOf[PhotoDataRepository])

    bind(classOf[GenreRepository]).to(classOf[GenreDataRepository])
  }

}
