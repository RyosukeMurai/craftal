package net.craftal.web.model.artist

import net.craftal.core.domain.model.photo.Photo

case class ArtistDescriptor(id: Int,
                            name: String,
                            email: String,
                            photos: Seq[Photo])
