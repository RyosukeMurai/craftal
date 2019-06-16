package net.craftal.core.domain.model.member

import net.craftal.common.domain.model.ValueObject

case class MemberPhoto(photoId: Int,
                       positionNo: Int) extends ValueObject[MemberPhoto] {
}