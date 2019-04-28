package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.prefecture.Prefecture

object PrefectureEntityDataMapper {
  def transform(prefectureEntity: Tables.PrefectureRow): Prefecture =
    Prefecture(
      id = prefectureEntity.id,
      name = prefectureEntity.name
    )

  def transformCollection(prefectureRows: List[Tables.PrefectureRow]): List[Prefecture] =
    prefectureRows.map(this.transform)
}
