package net.craftal.web.model.artist

import net.craftal.web.model.photo.PhotoDescriptor

case class ArtistDescriptor(id: Int,
                            name: String,
                            email: String,
                            prefecture: String,
                            artistPhoto: PhotoDescriptor)
