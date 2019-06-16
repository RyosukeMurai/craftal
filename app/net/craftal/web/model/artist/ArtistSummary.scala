package net.craftal.web.model.artist

import net.craftal.core.domain.model.genre.Genre

case class ArtistSummary(artists: Map[Genre, Seq[ArtistDescriptor]])
