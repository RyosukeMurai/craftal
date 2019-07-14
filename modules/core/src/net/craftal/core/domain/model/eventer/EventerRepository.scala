package net.craftal.core.domain.model.eventer

import scala.concurrent.Future

trait EventerRepository {

  def findEventer(eventerId: Int): Future[Eventer]

  def findAll(): Future[List[Eventer]]

  def createEventer(userId: Int): Future[Eventer]

  def updateEventerProfile(eventerId: Int,
                           name: Option[String]): Future[Eventer]
}
