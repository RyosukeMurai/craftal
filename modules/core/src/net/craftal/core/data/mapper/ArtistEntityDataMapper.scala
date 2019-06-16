package net.craftal.core.data.mapper

import net.craftal.common.data.Tables
import net.craftal.core.domain.model.artist.{Artist, ArtistAttribute, ArtistPhoto}

object ArtistEntityDataMapper {
  def transform(entity: (Tables.UserRow, Tables.ArtistRow, List[Tables.ArtistPhotoRow], List[Tables.ArtistAttributeRow])): Artist =
    Artist(
      id = entity._1.id,
      name = entity._1.name,
      email = entity._1.email,
      genreId = entity._2.genreId,
      prefectureId = entity._2.prefectureId,
      aboutInquiry = entity._2.aboutInquiry,
      homePageUrl = entity._2.homePageUrl,
      shopPageUrl = entity._2.shopPageUrl,
      twitterUrl = entity._2.twitterUrl,
      facebookUrl = entity._2.facebookUrl,
      instagramUrl = entity._2.instagramUrl,
      selfIntroduction = entity._2.selfIntroduction,
      entity._3.map(photo => ArtistPhoto(
        photoId = photo.photoId,
        positionNo = photo.positionNo
      )),
      entity._4.map(attribute => ArtistAttribute(
        attributeId = attribute.attributeId
      ))
    )

  def transformCollection(entities: List[(Tables.UserRow, Tables.ArtistRow, Tables.ArtistPhotoRow, Tables.ArtistAttributeRow)]): List[Artist] = {
    entities
      .groupBy(_._1.id)
      .map(m => this.transform(m._2.head._1, m._2.head._2, m._2.map(_._3), m._2.map(_._4)))
      .toList
  }

}
