package net.craftal.core.domain.model.event

object EventStatus extends Enumeration {
  type EventStatus = Value
  val draft, applicable, confirmed = Value
}
