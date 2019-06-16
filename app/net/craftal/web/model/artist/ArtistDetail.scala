package net.craftal.web.model.artist

import net.craftal.web.model.attribute.AttributeDescriptor
import net.craftal.web.model.event.EventDescriptor
import net.craftal.web.model.photo.PhotoDescriptor

case class ArtistDetail(id: Int,
                        name: String,
                        email: String,
                        genre: String,
                        prefecture: String,
                        aboutInquiry: String,
                        homePageUrl: String,
                        shopPageUrl: String,
                        twitterUrl: String,
                        facebookUrl: String,
                        instagramUrl: String,
                        selfIntroduction: String,
                        coverPhoto: PhotoDescriptor,
                        iconPhoto: PhotoDescriptor,
                        subPhotos: List[PhotoDescriptor],
                        events: Seq[EventDescriptor],
                        attributes: List[AttributeDescriptor]) {

  def eventsParticipateInThePresent: Seq[EventDescriptor] = this.events

  def eventsParticipatedInThePast: Seq[EventDescriptor] = this.events
}
