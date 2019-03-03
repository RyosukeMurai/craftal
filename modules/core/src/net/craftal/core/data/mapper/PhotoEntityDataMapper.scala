package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.photo.Photo

object PhotoEntityDataMapper {
  def transform(photoEntity: Tables.PhotoRow): Photo =
    Photo(
      id = photoEntity.id,
      identifier = photoEntity.fileIdentifier,
      name = photoEntity.fileName,
      userId = photoEntity.userId
    )

  def transformCollection(photoRows: List[Tables.PhotoRow]): List[Photo] =
    photoRows
      .map(m => this.transform(m))
}
