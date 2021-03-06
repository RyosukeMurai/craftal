package net.craftal.web

import com.google.inject.AbstractModule
import net.craftal.core.data.store._
import net.craftal.core.domain.model.artist.ArtistRepository
import net.craftal.core.domain.model.attribute.AttributeRepository
import net.craftal.core.domain.model.event.EventRepository
import net.craftal.core.domain.model.eventer.EventerRepository
import net.craftal.core.domain.model.genre.GenreRepository
import net.craftal.core.domain.model.member.MemberRepository
import net.craftal.core.domain.model.photo.PhotoRepository
import net.craftal.core.domain.model.prefecture.PrefectureRepository
import net.craftal.identityaccess.data.store.{IdentityDataStore, RoleDataStore, UserDataStore}
import net.craftal.identityaccess.domain.model.identity.IdentityRepository
import net.craftal.identityaccess.domain.model.role.RoleRepository
import net.craftal.identityaccess.domain.model.user.UserRepository

class DataModule extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[UserRepository]).to(classOf[UserDataStore])

    bind(classOf[IdentityRepository]).to(classOf[IdentityDataStore])

    bind(classOf[EventRepository]).to(classOf[EventDataStore])

    bind(classOf[MemberRepository]).to(classOf[MemberDataStore])

    bind(classOf[ArtistRepository]).to(classOf[ArtistDataStore])

    bind(classOf[PhotoRepository]).to(classOf[PhotoDataStore])

    bind(classOf[GenreRepository]).to(classOf[GenreDataStore])

    bind(classOf[RoleRepository]).to(classOf[RoleDataStore])

    bind(classOf[PrefectureRepository]).to(classOf[PrefectureDataStore])

    bind(classOf[AttributeRepository]).to(classOf[AttributeDataStore])

    bind(classOf[EventerRepository]).to(classOf[EventerDataStore])
  }

}
