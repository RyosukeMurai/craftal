package domain.model.artist

import domain.model.shared.ValueObject

case class ArtistPhoto(artistId: Int,
                       photoId: Int,
                       positionNo: Int) extends ValueObject[ArtistPhoto] {
}