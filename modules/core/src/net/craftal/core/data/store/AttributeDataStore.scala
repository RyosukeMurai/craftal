package net.craftal.core.data.store

import javax.inject.Inject
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.AttributeEntityDataMapper
import net.craftal.core.domain.model.attribute.{Attribute, AttributeRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class AttributeDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends AttributeRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findAttribute(attributeId: Int): Future[Attribute] =
    dbConfig.db
      .run(Tables.Attribute.filter(_.id === attributeId).result.head.map(AttributeEntityDataMapper.transform))

  override def findAttributesByIdList(idList: List[Int]): Future[List[Attribute]] =
    dbConfig.db
      .run(
        Tables.Attribute.filter(_.id.inSet(idList)).to[List].result.map(AttributeEntityDataMapper.transformCollection)
      )

}

