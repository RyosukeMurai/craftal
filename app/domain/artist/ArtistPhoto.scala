package domain.artist

import domain.shared.ValueObject

case class ArtistPhoto(artistId: Int,
                       photoId: Int,
                       positionNo: Int) extends ValueObject[ArtistPhoto] {
}