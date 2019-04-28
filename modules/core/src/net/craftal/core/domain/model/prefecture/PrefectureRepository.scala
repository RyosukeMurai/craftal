package net.craftal.core.domain.model.prefecture

import scala.concurrent.Future

trait PrefectureRepository {

  def findPrefecture(prefectureId: Int): Future[Prefecture]

  def findPrefecturesByIdList(idList: List[Int]): Future[List[Prefecture]]

  def findAll(): Future[List[Prefecture]]
}
