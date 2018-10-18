package data.entity.mapper

import data.entity.Tables
import domain.genre.Genre

object GenreEntityDataMapper {
  def transform(genreEntity: Tables.GenreRow, parent: Option[Tables.GenreRow]): Genre =
    Genre(
      id = genreEntity.id,
      name = genreEntity.name,
      parent = parent.map(g => Genre(g.id, g.name, None))
    )

  def transform(entity: (Tables.GenreRow, Option[Tables.GenreRow])): Genre =
    this.transform(entity._1, entity._2)

  def transformCollection(genreRows: List[(Tables.GenreRow, Option[Tables.GenreRow])]): List[Genre] =
    genreRows
      .map(m => this.transform(m._1, m._2))
      .toList
}
