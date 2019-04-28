package net.craftal.core.data.store

import javax.inject.Inject
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.PrefectureEntityDataMapper
import net.craftal.core.domain.model.prefecture.{Prefecture, PrefectureRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class PrefectureDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends PrefectureRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findPrefecture(prefectureId: Int): Future[Prefecture] = {
    dbConfig
      .db
      .run(
        Tables.Prefecture.filter(_.id === prefectureId).result.head.map(PrefectureEntityDataMapper.transform)
      )
  }

  override def findPrefecturesByIdList(idList: List[Int]): Future[List[Prefecture]] =
    dbConfig.db
      .run(
        Tables.Prefecture.filter(_.id.inSet(idList)).to[List].result.map(PrefectureEntityDataMapper.transformCollection)
      )

  override def findAll(): Future[List[Prefecture]] = {
    dbConfig
      .db
      .run(
        Tables.Prefecture.to[List].result.map(PrefectureEntityDataMapper.transformCollection)
      )
  }
}

