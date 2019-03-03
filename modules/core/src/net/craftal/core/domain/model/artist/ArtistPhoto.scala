package net.craftal.core.domain.model.artist

import net.craftal.common.domain.model.ValueObject

case class ArtistPhoto(photoId: Int,
                       positionNo: Int) extends ValueObject[ArtistPhoto] {
}