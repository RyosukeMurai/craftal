package net.craftal.core.data.store

import javax.inject.Inject
import net.craftal.common.data.Tables
import net.craftal.core.data.mapper.MemberEntityDataMapper
import net.craftal.core.domain.model.member.{Member, MemberRepository}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class MemberDataStore @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends MemberRepository {

  import slick.jdbc.MySQLProfile.api._

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findMember(id: Int): Future[Member] = {
    val query = for {
      u <- Tables.User if u.id === id
    } yield u

    dbConfig.db
      .run(query.to[List].result.map(MemberEntityDataMapper.transformCollection(_).head))
  }

  override def findMembersByKeyword(keyword: Option[String]): Future[List[Member]] = {
    val query = for {
      u <- Tables.User
    } yield u

    dbConfig
      .db
      .run(
        query.to[List].result.map(MemberEntityDataMapper.transformCollection)
      )
  }

  override def findAll(): Future[List[Member]] = {
    val query = for {
      u <- Tables.User
    } yield u

    dbConfig
      .db
      .run(
        query.to[List].result.map(MemberEntityDataMapper.transformCollection)
      )
  }
}

