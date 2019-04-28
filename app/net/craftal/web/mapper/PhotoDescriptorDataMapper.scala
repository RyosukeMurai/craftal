package net.craftal.web.mapper

import net.craftal.core.application.service.{ResourceType, URIService}
import net.craftal.core.domain.model.photo.Photo
import net.craftal.web.model.photo.PhotoDescriptor


object PhotoDescriptorDataMapper extends DataMapper {
  def transform(photo: Photo): PhotoDescriptor =
    PhotoDescriptor(
      URIService.resolveUrlByIdentifierAndResourceType(photo.identifier, ResourceType.photo).toString,
      photo.name
    )
}
