package net.craftal.web.model.mypage

import net.craftal.web.model.artist.ArtistDescriptor
import net.craftal.web.model.event.EventDescriptor

case class Mypage(artists: Seq[ArtistDescriptor],
                  events: Seq[EventDescriptor])
