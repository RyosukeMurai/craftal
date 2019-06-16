package net.craftal.common.data
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Artist.schema, ArtistAttribute.schema, ArtistFollower.schema, ArtistPhoto.schema, Attribute.schema, AttributeType.schema, Event.schema, EventArtist.schema, EventAttribute.schema, EventLocation.schema, EventPhoto.schema, EventSchedule.schema, EventStatus.schema, Genre.schema, Organizer.schema, Permission.schema, Photo.schema, PlayEvolutions.schema, Prefecture.schema, Role.schema, RolePermission.schema, User.schema, UserIdentity.schema, UserIdentityPassword.schema, UserIdentityToken.schema, UserRole.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Artist
   *  @param userId Database column user_id SqlType(INT UNSIGNED), PrimaryKey
   *  @param genreId Database column genre_id SqlType(INT UNSIGNED)
   *  @param prefectureId Database column prefecture_id SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false)
   *  @param selfIntroduction Database column self_introduction SqlType(TEXT)
   *  @param aboutInquiry Database column about_inquiry SqlType(TEXT)
   *  @param homePageUrl Database column home_page_url SqlType(VARCHAR), Length(255,true)
   *  @param shopPageUrl Database column shop_page_url SqlType(VARCHAR), Length(255,true)
   *  @param twitterUrl Database column twitter_url SqlType(VARCHAR), Length(255,true)
   *  @param instagramUrl Database column instagram_url SqlType(VARCHAR), Length(255,true)
   *  @param facebookUrl Database column facebook_url SqlType(VARCHAR), Length(255,true) */
  case class ArtistRow(userId: Int, genreId: Int, prefectureId: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false, selfIntroduction: String, aboutInquiry: String, homePageUrl: String, shopPageUrl: String, twitterUrl: String, instagramUrl: String, facebookUrl: String)
  /** GetResult implicit for fetching ArtistRow objects using plain SQL queries */
  implicit def GetResultArtistRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Boolean], e3: GR[String]): GR[ArtistRow] = GR{
    prs => import prs._
    ArtistRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table artist. Objects of this class serve as prototypes for rows in queries. */
  class Artist(_tableTag: Tag) extends profile.api.Table[ArtistRow](_tableTag, Some("craftal"), "artist") {
    def * = (userId, genreId, prefectureId, createdAt, updatedAt, isDeleted, selfIntroduction, aboutInquiry, homePageUrl, shopPageUrl, twitterUrl, instagramUrl, facebookUrl) <> (ArtistRow.tupled, ArtistRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(genreId), Rep.Some(prefectureId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted), Rep.Some(selfIntroduction), Rep.Some(aboutInquiry), Rep.Some(homePageUrl), Rep.Some(shopPageUrl), Rep.Some(twitterUrl), Rep.Some(instagramUrl), Rep.Some(facebookUrl)).shaped.<>({r=>import r._; _1.map(_=> ArtistRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(INT UNSIGNED), PrimaryKey */
    val userId: Rep[Int] = column[Int]("user_id", O.PrimaryKey)
    /** Database column genre_id SqlType(INT UNSIGNED) */
    val genreId: Rep[Int] = column[Int]("genre_id")
    /** Database column prefecture_id SqlType(INT UNSIGNED) */
    val prefectureId: Rep[Int] = column[Int]("prefecture_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))
    /** Database column self_introduction SqlType(TEXT) */
    val selfIntroduction: Rep[String] = column[String]("self_introduction")
    /** Database column about_inquiry SqlType(TEXT) */
    val aboutInquiry: Rep[String] = column[String]("about_inquiry")
    /** Database column home_page_url SqlType(VARCHAR), Length(255,true) */
    val homePageUrl: Rep[String] = column[String]("home_page_url", O.Length(255,varying=true))
    /** Database column shop_page_url SqlType(VARCHAR), Length(255,true) */
    val shopPageUrl: Rep[String] = column[String]("shop_page_url", O.Length(255,varying=true))
    /** Database column twitter_url SqlType(VARCHAR), Length(255,true) */
    val twitterUrl: Rep[String] = column[String]("twitter_url", O.Length(255,varying=true))
    /** Database column instagram_url SqlType(VARCHAR), Length(255,true) */
    val instagramUrl: Rep[String] = column[String]("instagram_url", O.Length(255,varying=true))
    /** Database column facebook_url SqlType(VARCHAR), Length(255,true) */
    val facebookUrl: Rep[String] = column[String]("facebook_url", O.Length(255,varying=true))

    /** Foreign key referencing Genre (database name artist_ibfk_2) */
    lazy val genreFk = foreignKey("artist_ibfk_2", genreId, Genre)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Prefecture (database name artist_ibfk_3) */
    lazy val prefectureFk = foreignKey("artist_ibfk_3", prefectureId, Prefecture)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name artist_ibfk_1) */
    lazy val userFk = foreignKey("artist_ibfk_1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Artist */
  lazy val Artist = new TableQuery(tag => new Artist(tag))

  /** Entity class storing rows of table ArtistAttribute
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
   *  @param artistId Database column artist_id SqlType(INT UNSIGNED)
   *  @param attributeId Database column attribute_id SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class ArtistAttributeRow(id: Int, artistId: Int, attributeId: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching ArtistAttributeRow objects using plain SQL queries */
  implicit def GetResultArtistAttributeRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Boolean]): GR[ArtistAttributeRow] = GR{
    prs => import prs._
    ArtistAttributeRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table artist_attribute. Objects of this class serve as prototypes for rows in queries. */
  class ArtistAttribute(_tableTag: Tag) extends profile.api.Table[ArtistAttributeRow](_tableTag, Some("craftal"), "artist_attribute") {
    def * = (id, artistId, attributeId, createdAt, updatedAt, isDeleted) <> (ArtistAttributeRow.tupled, ArtistAttributeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(artistId), Rep.Some(attributeId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> ArtistAttributeRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column artist_id SqlType(INT UNSIGNED) */
    val artistId: Rep[Int] = column[Int]("artist_id")
    /** Database column attribute_id SqlType(INT UNSIGNED) */
    val attributeId: Rep[Int] = column[Int]("attribute_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Artist (database name artist_attribute_ibfk_1) */
    lazy val artistFk = foreignKey("artist_attribute_ibfk_1", artistId, Artist)(r => r.userId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Attribute (database name artist_attribute_ibfk_2) */
    lazy val attributeFk = foreignKey("artist_attribute_ibfk_2", attributeId, Attribute)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ArtistAttribute */
  lazy val ArtistAttribute = new TableQuery(tag => new ArtistAttribute(tag))

  /** Entity class storing rows of table ArtistFollower
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param artistId Database column artist_id SqlType(INT UNSIGNED)
   *  @param followerId Database column follower_id SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class ArtistFollowerRow(id: Int, artistId: Int, followerId: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching ArtistFollowerRow objects using plain SQL queries */
  implicit def GetResultArtistFollowerRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Boolean]): GR[ArtistFollowerRow] = GR{
    prs => import prs._
    ArtistFollowerRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table artist_follower. Objects of this class serve as prototypes for rows in queries. */
  class ArtistFollower(_tableTag: Tag) extends profile.api.Table[ArtistFollowerRow](_tableTag, Some("craftal"), "artist_follower") {
    def * = (id, artistId, followerId, createdAt, updatedAt, isDeleted) <> (ArtistFollowerRow.tupled, ArtistFollowerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(artistId), Rep.Some(followerId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> ArtistFollowerRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column artist_id SqlType(INT UNSIGNED) */
    val artistId: Rep[Int] = column[Int]("artist_id")
    /** Database column follower_id SqlType(INT UNSIGNED) */
    val followerId: Rep[Int] = column[Int]("follower_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Artist (database name artist_follower_ibfk_1) */
    lazy val artistFk = foreignKey("artist_follower_ibfk_1", artistId, Artist)(r => r.userId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name artist_follower_ibfk_2) */
    lazy val userFk = foreignKey("artist_follower_ibfk_2", followerId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (artistId,followerId) (database name artist_id) */
    val index1 = index("artist_id", (artistId, followerId), unique=true)
  }
  /** Collection-like TableQuery object for table ArtistFollower */
  lazy val ArtistFollower = new TableQuery(tag => new ArtistFollower(tag))

  /** Entity class storing rows of table ArtistPhoto
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param artistId Database column artist_id SqlType(INT UNSIGNED)
   *  @param photoId Database column photo_id SqlType(INT UNSIGNED)
   *  @param positionNo Database column position_no SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class ArtistPhotoRow(id: Int, artistId: Int, photoId: Int, positionNo: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching ArtistPhotoRow objects using plain SQL queries */
  implicit def GetResultArtistPhotoRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Boolean]): GR[ArtistPhotoRow] = GR{
    prs => import prs._
    ArtistPhotoRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table artist_photo. Objects of this class serve as prototypes for rows in queries. */
  class ArtistPhoto(_tableTag: Tag) extends profile.api.Table[ArtistPhotoRow](_tableTag, Some("craftal"), "artist_photo") {
    def * = (id, artistId, photoId, positionNo, createdAt, updatedAt, isDeleted) <> (ArtistPhotoRow.tupled, ArtistPhotoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(artistId), Rep.Some(photoId), Rep.Some(positionNo), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> ArtistPhotoRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column artist_id SqlType(INT UNSIGNED) */
    val artistId: Rep[Int] = column[Int]("artist_id")
    /** Database column photo_id SqlType(INT UNSIGNED) */
    val photoId: Rep[Int] = column[Int]("photo_id")
    /** Database column position_no SqlType(INT UNSIGNED) */
    val positionNo: Rep[Int] = column[Int]("position_no")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Artist (database name artist_photo_ibfk_1) */
    lazy val artistFk = foreignKey("artist_photo_ibfk_1", artistId, Artist)(r => r.userId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Photo (database name artist_photo_ibfk_2) */
    lazy val photoFk = foreignKey("artist_photo_ibfk_2", photoId, Photo)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ArtistPhoto */
  lazy val ArtistPhoto = new TableQuery(tag => new ArtistPhoto(tag))

  /** Entity class storing rows of table Attribute
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true)
   *  @param typeId Database column type_id SqlType(TINYINT UNSIGNED) */
  case class AttributeRow(id: Int, name: String, typeId: Byte)
  /** GetResult implicit for fetching AttributeRow objects using plain SQL queries */
  implicit def GetResultAttributeRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Byte]): GR[AttributeRow] = GR{
    prs => import prs._
    AttributeRow.tupled((<<[Int], <<[String], <<[Byte]))
  }
  /** Table description of table attribute. Objects of this class serve as prototypes for rows in queries. */
  class Attribute(_tableTag: Tag) extends profile.api.Table[AttributeRow](_tableTag, Some("craftal"), "attribute") {
    def * = (id, name, typeId) <> (AttributeRow.tupled, AttributeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(typeId)).shaped.<>({r=>import r._; _1.map(_=> AttributeRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column type_id SqlType(TINYINT UNSIGNED) */
    val typeId: Rep[Byte] = column[Byte]("type_id")

    /** Foreign key referencing AttributeType (database name attribute_ibfk_1) */
    lazy val attributeTypeFk = foreignKey("attribute_ibfk_1", typeId, AttributeType)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Attribute */
  lazy val Attribute = new TableQuery(tag => new Attribute(tag))

  /** Entity class storing rows of table AttributeType
   *  @param id Database column id SqlType(TINYINT UNSIGNED), PrimaryKey
   *  @param code Database column code SqlType(ENUM), Length(21,false) */
  case class AttributeTypeRow(id: Byte, code: String)
  /** GetResult implicit for fetching AttributeTypeRow objects using plain SQL queries */
  implicit def GetResultAttributeTypeRow(implicit e0: GR[Byte], e1: GR[String]): GR[AttributeTypeRow] = GR{
    prs => import prs._
    AttributeTypeRow.tupled((<<[Byte], <<[String]))
  }
  /** Table description of table attribute_type. Objects of this class serve as prototypes for rows in queries. */
  class AttributeType(_tableTag: Tag) extends profile.api.Table[AttributeTypeRow](_tableTag, Some("craftal"), "attribute_type") {
    def * = (id, code) <> (AttributeTypeRow.tupled, AttributeTypeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(code)).shaped.<>({r=>import r._; _1.map(_=> AttributeTypeRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(TINYINT UNSIGNED), PrimaryKey */
    val id: Rep[Byte] = column[Byte]("id", O.PrimaryKey)
    /** Database column code SqlType(ENUM), Length(21,false) */
    val code: Rep[String] = column[String]("code", O.Length(21,varying=false))
  }
  /** Collection-like TableQuery object for table AttributeType */
  lazy val AttributeType = new TableQuery(tag => new AttributeType(tag))

  /** Entity class storing rows of table Event
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param title Database column title SqlType(VARCHAR), Length(255,true)
   *  @param description Database column description SqlType(TEXT)
   *  @param statusId Database column status_id SqlType(TINYINT UNSIGNED)
   *  @param locationId Database column location_id SqlType(TINYINT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false)
   *  @param homePageUrl Database column home_page_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param twitterUrl Database column twitter_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param instagramUrl Database column instagram_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param facebookUrl Database column facebook_url SqlType(VARCHAR), Length(255,true), Default(None) */
  case class EventRow(id: Int, title: String, description: String, statusId: Byte, locationId: Byte, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false, homePageUrl: Option[String] = None, twitterUrl: Option[String] = None, instagramUrl: Option[String] = None, facebookUrl: Option[String] = None)
  /** GetResult implicit for fetching EventRow objects using plain SQL queries */
  implicit def GetResultEventRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Byte], e3: GR[java.sql.Timestamp], e4: GR[Boolean], e5: GR[Option[String]]): GR[EventRow] = GR{
    prs => import prs._
    EventRow.tupled((<<[Int], <<[String], <<[String], <<[Byte], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table event. Objects of this class serve as prototypes for rows in queries. */
  class Event(_tableTag: Tag) extends profile.api.Table[EventRow](_tableTag, Some("craftal"), "event") {
    def * = (id, title, description, statusId, locationId, createdAt, updatedAt, isDeleted, homePageUrl, twitterUrl, instagramUrl, facebookUrl) <> (EventRow.tupled, EventRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(title), Rep.Some(description), Rep.Some(statusId), Rep.Some(locationId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted), homePageUrl, twitterUrl, instagramUrl, facebookUrl).shaped.<>({r=>import r._; _1.map(_=> EventRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9, _10, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column title SqlType(VARCHAR), Length(255,true) */
    val title: Rep[String] = column[String]("title", O.Length(255,varying=true))
    /** Database column description SqlType(TEXT) */
    val description: Rep[String] = column[String]("description")
    /** Database column status_id SqlType(TINYINT UNSIGNED) */
    val statusId: Rep[Byte] = column[Byte]("status_id")
    /** Database column location_id SqlType(TINYINT UNSIGNED) */
    val locationId: Rep[Byte] = column[Byte]("location_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))
    /** Database column home_page_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val homePageUrl: Rep[Option[String]] = column[Option[String]]("home_page_url", O.Length(255,varying=true), O.Default(None))
    /** Database column twitter_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val twitterUrl: Rep[Option[String]] = column[Option[String]]("twitter_url", O.Length(255,varying=true), O.Default(None))
    /** Database column instagram_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val instagramUrl: Rep[Option[String]] = column[Option[String]]("instagram_url", O.Length(255,varying=true), O.Default(None))
    /** Database column facebook_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val facebookUrl: Rep[Option[String]] = column[Option[String]]("facebook_url", O.Length(255,varying=true), O.Default(None))

    /** Foreign key referencing EventLocation (database name event_ibfk_2) */
    lazy val eventLocationFk = foreignKey("event_ibfk_2", locationId, EventLocation)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing EventStatus (database name event_ibfk_1) */
    lazy val eventStatusFk = foreignKey("event_ibfk_1", statusId, EventStatus)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Event */
  lazy val Event = new TableQuery(tag => new Event(tag))

  /** Entity class storing rows of table EventArtist
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
   *  @param eventId Database column event_id SqlType(INT UNSIGNED)
   *  @param artistId Database column artist_id SqlType(INT UNSIGNED), Default(None)
   *  @param positionNo Database column position_no SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false)
   *  @param artistComment Database column artist_comment SqlType(TEXT)
   *  @param comment Database column comment SqlType(TEXT) */
  case class EventArtistRow(id: Int, eventId: Int, artistId: Option[Int] = None, positionNo: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false, artistComment: String, comment: String)
  /** GetResult implicit for fetching EventArtistRow objects using plain SQL queries */
  implicit def GetResultEventArtistRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp], e3: GR[Boolean], e4: GR[String]): GR[EventArtistRow] = GR{
    prs => import prs._
    EventArtistRow.tupled((<<[Int], <<[Int], <<?[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean], <<[String], <<[String]))
  }
  /** Table description of table event_artist. Objects of this class serve as prototypes for rows in queries. */
  class EventArtist(_tableTag: Tag) extends profile.api.Table[EventArtistRow](_tableTag, Some("craftal"), "event_artist") {
    def * = (id, eventId, artistId, positionNo, createdAt, updatedAt, isDeleted, artistComment, comment) <> (EventArtistRow.tupled, EventArtistRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(eventId), artistId, Rep.Some(positionNo), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted), Rep.Some(artistComment), Rep.Some(comment)).shaped.<>({r=>import r._; _1.map(_=> EventArtistRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column event_id SqlType(INT UNSIGNED) */
    val eventId: Rep[Int] = column[Int]("event_id")
    /** Database column artist_id SqlType(INT UNSIGNED), Default(None) */
    val artistId: Rep[Option[Int]] = column[Option[Int]]("artist_id", O.Default(None))
    /** Database column position_no SqlType(INT UNSIGNED) */
    val positionNo: Rep[Int] = column[Int]("position_no")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))
    /** Database column artist_comment SqlType(TEXT) */
    val artistComment: Rep[String] = column[String]("artist_comment")
    /** Database column comment SqlType(TEXT) */
    val comment: Rep[String] = column[String]("comment")

    /** Foreign key referencing Artist (database name event_artist_ibfk_2) */
    lazy val artistFk = foreignKey("event_artist_ibfk_2", artistId, Artist)(r => Rep.Some(r.userId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Event (database name event_artist_ibfk_1) */
    lazy val eventFk = foreignKey("event_artist_ibfk_1", eventId, Event)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table EventArtist */
  lazy val EventArtist = new TableQuery(tag => new EventArtist(tag))

  /** Entity class storing rows of table EventAttribute
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
   *  @param eventId Database column event_id SqlType(INT UNSIGNED)
   *  @param attributeId Database column attribute_id SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class EventAttributeRow(id: Int, eventId: Int, attributeId: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching EventAttributeRow objects using plain SQL queries */
  implicit def GetResultEventAttributeRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Boolean]): GR[EventAttributeRow] = GR{
    prs => import prs._
    EventAttributeRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table event_attribute. Objects of this class serve as prototypes for rows in queries. */
  class EventAttribute(_tableTag: Tag) extends profile.api.Table[EventAttributeRow](_tableTag, Some("craftal"), "event_attribute") {
    def * = (id, eventId, attributeId, createdAt, updatedAt, isDeleted) <> (EventAttributeRow.tupled, EventAttributeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(eventId), Rep.Some(attributeId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> EventAttributeRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column event_id SqlType(INT UNSIGNED) */
    val eventId: Rep[Int] = column[Int]("event_id")
    /** Database column attribute_id SqlType(INT UNSIGNED) */
    val attributeId: Rep[Int] = column[Int]("attribute_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Attribute (database name event_attribute_ibfk_2) */
    lazy val attributeFk = foreignKey("event_attribute_ibfk_2", attributeId, Attribute)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Event (database name event_attribute_ibfk_1) */
    lazy val eventFk = foreignKey("event_attribute_ibfk_1", eventId, Event)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table EventAttribute */
  lazy val EventAttribute = new TableQuery(tag => new EventAttribute(tag))

  /** Entity class storing rows of table EventLocation
   *  @param id Database column id SqlType(TINYINT UNSIGNED), PrimaryKey
   *  @param code Database column code SqlType(ENUM), Length(8,false) */
  case class EventLocationRow(id: Byte, code: String)
  /** GetResult implicit for fetching EventLocationRow objects using plain SQL queries */
  implicit def GetResultEventLocationRow(implicit e0: GR[Byte], e1: GR[String]): GR[EventLocationRow] = GR{
    prs => import prs._
    EventLocationRow.tupled((<<[Byte], <<[String]))
  }
  /** Table description of table event_location. Objects of this class serve as prototypes for rows in queries. */
  class EventLocation(_tableTag: Tag) extends profile.api.Table[EventLocationRow](_tableTag, Some("craftal"), "event_location") {
    def * = (id, code) <> (EventLocationRow.tupled, EventLocationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(code)).shaped.<>({r=>import r._; _1.map(_=> EventLocationRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(TINYINT UNSIGNED), PrimaryKey */
    val id: Rep[Byte] = column[Byte]("id", O.PrimaryKey)
    /** Database column code SqlType(ENUM), Length(8,false) */
    val code: Rep[String] = column[String]("code", O.Length(8,varying=false))
  }
  /** Collection-like TableQuery object for table EventLocation */
  lazy val EventLocation = new TableQuery(tag => new EventLocation(tag))

  /** Entity class storing rows of table EventPhoto
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param eventId Database column event_id SqlType(INT UNSIGNED)
   *  @param photoId Database column photo_id SqlType(INT UNSIGNED)
   *  @param positionNo Database column position_no SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class EventPhotoRow(id: Int, eventId: Int, photoId: Int, positionNo: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching EventPhotoRow objects using plain SQL queries */
  implicit def GetResultEventPhotoRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Boolean]): GR[EventPhotoRow] = GR{
    prs => import prs._
    EventPhotoRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table event_photo. Objects of this class serve as prototypes for rows in queries. */
  class EventPhoto(_tableTag: Tag) extends profile.api.Table[EventPhotoRow](_tableTag, Some("craftal"), "event_photo") {
    def * = (id, eventId, photoId, positionNo, createdAt, updatedAt, isDeleted) <> (EventPhotoRow.tupled, EventPhotoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(eventId), Rep.Some(photoId), Rep.Some(positionNo), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> EventPhotoRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column event_id SqlType(INT UNSIGNED) */
    val eventId: Rep[Int] = column[Int]("event_id")
    /** Database column photo_id SqlType(INT UNSIGNED) */
    val photoId: Rep[Int] = column[Int]("photo_id")
    /** Database column position_no SqlType(INT UNSIGNED) */
    val positionNo: Rep[Int] = column[Int]("position_no")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Event (database name event_photo_ibfk_1) */
    lazy val eventFk = foreignKey("event_photo_ibfk_1", eventId, Event)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Photo (database name event_photo_ibfk_2) */
    lazy val photoFk = foreignKey("event_photo_ibfk_2", photoId, Photo)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table EventPhoto */
  lazy val EventPhoto = new TableQuery(tag => new EventPhoto(tag))

  /** Entity class storing rows of table EventSchedule
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param eventId Database column event_id SqlType(INT UNSIGNED)
   *  @param prefectureId Database column prefecture_id SqlType(INT UNSIGNED), Default(13)
   *  @param numOfBooths Database column num_of_booths SqlType(INT UNSIGNED), Default(0)
   *  @param venue Database column venue SqlType(VARCHAR), Length(255,true)
   *  @param mapCoordinate Database column map_coordinate SqlType(GEOMETRY)
   *  @param startTime Database column start_time SqlType(DATETIME)
   *  @param endTime Database column end_time SqlType(DATETIME)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false)
   *  @param address Database column address SqlType(VARCHAR), Length(255,true)
   *  @param postalCode Database column postal_code SqlType(VARCHAR), Length(8,true)
   *  @param howToAccess Database column how_to_access SqlType(TEXT)
   *  @param venueUrl Database column venue_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param venueRemarks Database column venue_remarks SqlType(TEXT) */
  case class EventScheduleRow(id: Int, eventId: Int, prefectureId: Int = 13, numOfBooths: Int = 0, venue: String, mapCoordinate: java.sql.Blob, startTime: java.sql.Timestamp, endTime: java.sql.Timestamp, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false, address: String, postalCode: String, howToAccess: String, venueUrl: Option[String] = None, venueRemarks: String)
  /** GetResult implicit for fetching EventScheduleRow objects using plain SQL queries */
  implicit def GetResultEventScheduleRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Blob], e3: GR[java.sql.Timestamp], e4: GR[Boolean], e5: GR[Option[String]]): GR[EventScheduleRow] = GR{
    prs => import prs._
    EventScheduleRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[String], <<[java.sql.Blob], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean], <<[String], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table event_schedule. Objects of this class serve as prototypes for rows in queries. */
  class EventSchedule(_tableTag: Tag) extends profile.api.Table[EventScheduleRow](_tableTag, Some("craftal"), "event_schedule") {
    def * = (id, eventId, prefectureId, numOfBooths, venue, mapCoordinate, startTime, endTime, createdAt, updatedAt, isDeleted, address, postalCode, howToAccess, venueUrl, venueRemarks) <> (EventScheduleRow.tupled, EventScheduleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(eventId), Rep.Some(prefectureId), Rep.Some(numOfBooths), Rep.Some(venue), Rep.Some(mapCoordinate), Rep.Some(startTime), Rep.Some(endTime), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted), Rep.Some(address), Rep.Some(postalCode), Rep.Some(howToAccess), venueUrl, Rep.Some(venueRemarks)).shaped.<>({r=>import r._; _1.map(_=> EventScheduleRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get, _15, _16.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column event_id SqlType(INT UNSIGNED) */
    val eventId: Rep[Int] = column[Int]("event_id")
    /** Database column prefecture_id SqlType(INT UNSIGNED), Default(13) */
    val prefectureId: Rep[Int] = column[Int]("prefecture_id", O.Default(13))
    /** Database column num_of_booths SqlType(INT UNSIGNED), Default(0) */
    val numOfBooths: Rep[Int] = column[Int]("num_of_booths", O.Default(0))
    /** Database column venue SqlType(VARCHAR), Length(255,true) */
    val venue: Rep[String] = column[String]("venue", O.Length(255,varying=true))
    /** Database column map_coordinate SqlType(GEOMETRY) */
    val mapCoordinate: Rep[java.sql.Blob] = column[java.sql.Blob]("map_coordinate")
    /** Database column start_time SqlType(DATETIME) */
    val startTime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("start_time")
    /** Database column end_time SqlType(DATETIME) */
    val endTime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("end_time")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))
    /** Database column address SqlType(VARCHAR), Length(255,true) */
    val address: Rep[String] = column[String]("address", O.Length(255,varying=true))
    /** Database column postal_code SqlType(VARCHAR), Length(8,true) */
    val postalCode: Rep[String] = column[String]("postal_code", O.Length(8,varying=true))
    /** Database column how_to_access SqlType(TEXT) */
    val howToAccess: Rep[String] = column[String]("how_to_access")
    /** Database column venue_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val venueUrl: Rep[Option[String]] = column[Option[String]]("venue_url", O.Length(255,varying=true), O.Default(None))
    /** Database column venue_remarks SqlType(TEXT) */
    val venueRemarks: Rep[String] = column[String]("venue_remarks")

    /** Foreign key referencing Event (database name event_schedule_ibfk_1) */
    lazy val eventFk = foreignKey("event_schedule_ibfk_1", eventId, Event)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Prefecture (database name event_schedule_ibfk_2) */
    lazy val prefectureFk = foreignKey("event_schedule_ibfk_2", prefectureId, Prefecture)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table EventSchedule */
  lazy val EventSchedule = new TableQuery(tag => new EventSchedule(tag))

  /** Entity class storing rows of table EventStatus
   *  @param id Database column id SqlType(TINYINT UNSIGNED), PrimaryKey
   *  @param code Database column code SqlType(ENUM), Length(10,false) */
  case class EventStatusRow(id: Byte, code: String)
  /** GetResult implicit for fetching EventStatusRow objects using plain SQL queries */
  implicit def GetResultEventStatusRow(implicit e0: GR[Byte], e1: GR[String]): GR[EventStatusRow] = GR{
    prs => import prs._
    EventStatusRow.tupled((<<[Byte], <<[String]))
  }
  /** Table description of table event_status. Objects of this class serve as prototypes for rows in queries. */
  class EventStatus(_tableTag: Tag) extends profile.api.Table[EventStatusRow](_tableTag, Some("craftal"), "event_status") {
    def * = (id, code) <> (EventStatusRow.tupled, EventStatusRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(code)).shaped.<>({r=>import r._; _1.map(_=> EventStatusRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(TINYINT UNSIGNED), PrimaryKey */
    val id: Rep[Byte] = column[Byte]("id", O.PrimaryKey)
    /** Database column code SqlType(ENUM), Length(10,false) */
    val code: Rep[String] = column[String]("code", O.Length(10,varying=false))
  }
  /** Collection-like TableQuery object for table EventStatus */
  lazy val EventStatus = new TableQuery(tag => new EventStatus(tag))

  /** Entity class storing rows of table Genre
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param parentId Database column parent_id SqlType(INT UNSIGNED), Default(None)
   *  @param name Database column name SqlType(VARCHAR), Length(255,true) */
  case class GenreRow(id: Int, parentId: Option[Int] = None, name: String)
  /** GetResult implicit for fetching GenreRow objects using plain SQL queries */
  implicit def GetResultGenreRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[String]): GR[GenreRow] = GR{
    prs => import prs._
    GenreRow.tupled((<<[Int], <<?[Int], <<[String]))
  }
  /** Table description of table genre. Objects of this class serve as prototypes for rows in queries. */
  class Genre(_tableTag: Tag) extends profile.api.Table[GenreRow](_tableTag, Some("craftal"), "genre") {
    def * = (id, parentId, name) <> (GenreRow.tupled, GenreRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), parentId, Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> GenreRow.tupled((_1.get, _2, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column parent_id SqlType(INT UNSIGNED), Default(None) */
    val parentId: Rep[Option[Int]] = column[Option[Int]]("parent_id", O.Default(None))
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))

    /** Foreign key referencing Genre (database name genre_ibfk_1) */
    lazy val genreFk = foreignKey("genre_ibfk_1", parentId, Genre)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Genre */
  lazy val Genre = new TableQuery(tag => new Genre(tag))

  /** Entity class storing rows of table Organizer
   *  @param userId Database column user_id SqlType(INT UNSIGNED), PrimaryKey
   *  @param phoneNumber Database column phone_number SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param faxNumber Database column fax_number SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param address Database column address SqlType(VARCHAR), Length(255,true)
   *  @param postalCode Database column postal_code SqlType(VARCHAR), Length(8,true)
   *  @param homePageUrl Database column home_page_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param twitterUrl Database column twitter_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param instagramUrl Database column instagram_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param facebookUrl Database column facebook_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class OrganizerRow(userId: Int, phoneNumber: Option[String] = None, faxNumber: Option[String] = None, address: String, postalCode: String, homePageUrl: Option[String] = None, twitterUrl: Option[String] = None, instagramUrl: Option[String] = None, facebookUrl: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching OrganizerRow objects using plain SQL queries */
  implicit def GetResultOrganizerRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String], e3: GR[java.sql.Timestamp], e4: GR[Boolean]): GR[OrganizerRow] = GR{
    prs => import prs._
    OrganizerRow.tupled((<<[Int], <<?[String], <<?[String], <<[String], <<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table organizer. Objects of this class serve as prototypes for rows in queries. */
  class Organizer(_tableTag: Tag) extends profile.api.Table[OrganizerRow](_tableTag, Some("craftal"), "organizer") {
    def * = (userId, phoneNumber, faxNumber, address, postalCode, homePageUrl, twitterUrl, instagramUrl, facebookUrl, createdAt, updatedAt, isDeleted) <> (OrganizerRow.tupled, OrganizerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), phoneNumber, faxNumber, Rep.Some(address), Rep.Some(postalCode), homePageUrl, twitterUrl, instagramUrl, facebookUrl, Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> OrganizerRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6, _7, _8, _9, _10.get, _11.get, _12.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(INT UNSIGNED), PrimaryKey */
    val userId: Rep[Int] = column[Int]("user_id", O.PrimaryKey)
    /** Database column phone_number SqlType(VARCHAR), Length(255,true), Default(None) */
    val phoneNumber: Rep[Option[String]] = column[Option[String]]("phone_number", O.Length(255,varying=true), O.Default(None))
    /** Database column fax_number SqlType(VARCHAR), Length(255,true), Default(None) */
    val faxNumber: Rep[Option[String]] = column[Option[String]]("fax_number", O.Length(255,varying=true), O.Default(None))
    /** Database column address SqlType(VARCHAR), Length(255,true) */
    val address: Rep[String] = column[String]("address", O.Length(255,varying=true))
    /** Database column postal_code SqlType(VARCHAR), Length(8,true) */
    val postalCode: Rep[String] = column[String]("postal_code", O.Length(8,varying=true))
    /** Database column home_page_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val homePageUrl: Rep[Option[String]] = column[Option[String]]("home_page_url", O.Length(255,varying=true), O.Default(None))
    /** Database column twitter_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val twitterUrl: Rep[Option[String]] = column[Option[String]]("twitter_url", O.Length(255,varying=true), O.Default(None))
    /** Database column instagram_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val instagramUrl: Rep[Option[String]] = column[Option[String]]("instagram_url", O.Length(255,varying=true), O.Default(None))
    /** Database column facebook_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val facebookUrl: Rep[Option[String]] = column[Option[String]]("facebook_url", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing User (database name organizer_ibfk_1) */
    lazy val userFk = foreignKey("organizer_ibfk_1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Organizer */
  lazy val Organizer = new TableQuery(tag => new Organizer(tag))

  /** Entity class storing rows of table Permission
   *  @param id Database column id SqlType(TINYINT UNSIGNED), PrimaryKey
   *  @param name Database column name SqlType(ENUM), Length(5,false)
   *  @param code Database column code SqlType(ENUM), Length(14,false) */
  case class PermissionRow(id: Byte, name: String, code: String)
  /** GetResult implicit for fetching PermissionRow objects using plain SQL queries */
  implicit def GetResultPermissionRow(implicit e0: GR[Byte], e1: GR[String]): GR[PermissionRow] = GR{
    prs => import prs._
    PermissionRow.tupled((<<[Byte], <<[String], <<[String]))
  }
  /** Table description of table permission. Objects of this class serve as prototypes for rows in queries. */
  class Permission(_tableTag: Tag) extends profile.api.Table[PermissionRow](_tableTag, Some("craftal"), "permission") {
    def * = (id, name, code) <> (PermissionRow.tupled, PermissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(code)).shaped.<>({r=>import r._; _1.map(_=> PermissionRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(TINYINT UNSIGNED), PrimaryKey */
    val id: Rep[Byte] = column[Byte]("id", O.PrimaryKey)
    /** Database column name SqlType(ENUM), Length(5,false) */
    val name: Rep[String] = column[String]("name", O.Length(5,varying=false))
    /** Database column code SqlType(ENUM), Length(14,false) */
    val code: Rep[String] = column[String]("code", O.Length(14,varying=false))

    /** Uniqueness Index over (code) (database name code) */
    val index1 = index("code", code, unique=true)
  }
  /** Collection-like TableQuery object for table Permission */
  lazy val Permission = new TableQuery(tag => new Permission(tag))

  /** Entity class storing rows of table Photo
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param fileIdentifier Database column file_identifier SqlType(VARCHAR), Length(255,true)
   *  @param fileName Database column file_name SqlType(VARCHAR), Length(255,true)
   *  @param userId Database column user_id SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false)
   *  @param caption Database column caption SqlType(VARCHAR), Length(255,true) */
  case class PhotoRow(id: Int, fileIdentifier: String, fileName: String, userId: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false, caption: String)
  /** GetResult implicit for fetching PhotoRow objects using plain SQL queries */
  implicit def GetResultPhotoRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[PhotoRow] = GR{
    prs => import prs._
    PhotoRow.tupled((<<[Int], <<[String], <<[String], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean], <<[String]))
  }
  /** Table description of table photo. Objects of this class serve as prototypes for rows in queries. */
  class Photo(_tableTag: Tag) extends profile.api.Table[PhotoRow](_tableTag, Some("craftal"), "photo") {
    def * = (id, fileIdentifier, fileName, userId, createdAt, updatedAt, isDeleted, caption) <> (PhotoRow.tupled, PhotoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(fileIdentifier), Rep.Some(fileName), Rep.Some(userId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted), Rep.Some(caption)).shaped.<>({r=>import r._; _1.map(_=> PhotoRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column file_identifier SqlType(VARCHAR), Length(255,true) */
    val fileIdentifier: Rep[String] = column[String]("file_identifier", O.Length(255,varying=true))
    /** Database column file_name SqlType(VARCHAR), Length(255,true) */
    val fileName: Rep[String] = column[String]("file_name", O.Length(255,varying=true))
    /** Database column user_id SqlType(INT UNSIGNED) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))
    /** Database column caption SqlType(VARCHAR), Length(255,true) */
    val caption: Rep[String] = column[String]("caption", O.Length(255,varying=true))

    /** Foreign key referencing User (database name photo_ibfk_1) */
    lazy val userFk = foreignKey("photo_ibfk_1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Photo */
  lazy val Photo = new TableQuery(tag => new Photo(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param hash Database column hash SqlType(VARCHAR), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(TIMESTAMP)
   *  @param applyScript Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param revertScript Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param state Database column state SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, Some("craftal"), "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(VARCHAR), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(TIMESTAMP) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column state SqlType(VARCHAR), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Length(16777215,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Prefecture
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true) */
  case class PrefectureRow(id: Int, name: String)
  /** GetResult implicit for fetching PrefectureRow objects using plain SQL queries */
  implicit def GetResultPrefectureRow(implicit e0: GR[Int], e1: GR[String]): GR[PrefectureRow] = GR{
    prs => import prs._
    PrefectureRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table prefecture. Objects of this class serve as prototypes for rows in queries. */
  class Prefecture(_tableTag: Tag) extends profile.api.Table[PrefectureRow](_tableTag, Some("craftal"), "prefecture") {
    def * = (id, name) <> (PrefectureRow.tupled, PrefectureRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> PrefectureRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table Prefecture */
  lazy val Prefecture = new TableQuery(tag => new Prefecture(tag))

  /** Entity class storing rows of table Role
   *  @param id Database column id SqlType(TINYINT UNSIGNED), PrimaryKey
   *  @param name Database column name SqlType(ENUM), Length(5,false)
   *  @param code Database column code SqlType(ENUM), Length(14,false) */
  case class RoleRow(id: Byte, name: String, code: String)
  /** GetResult implicit for fetching RoleRow objects using plain SQL queries */
  implicit def GetResultRoleRow(implicit e0: GR[Byte], e1: GR[String]): GR[RoleRow] = GR{
    prs => import prs._
    RoleRow.tupled((<<[Byte], <<[String], <<[String]))
  }
  /** Table description of table role. Objects of this class serve as prototypes for rows in queries. */
  class Role(_tableTag: Tag) extends profile.api.Table[RoleRow](_tableTag, Some("craftal"), "role") {
    def * = (id, name, code) <> (RoleRow.tupled, RoleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(code)).shaped.<>({r=>import r._; _1.map(_=> RoleRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(TINYINT UNSIGNED), PrimaryKey */
    val id: Rep[Byte] = column[Byte]("id", O.PrimaryKey)
    /** Database column name SqlType(ENUM), Length(5,false) */
    val name: Rep[String] = column[String]("name", O.Length(5,varying=false))
    /** Database column code SqlType(ENUM), Length(14,false) */
    val code: Rep[String] = column[String]("code", O.Length(14,varying=false))

    /** Uniqueness Index over (code) (database name code) */
    val index1 = index("code", code, unique=true)
  }
  /** Collection-like TableQuery object for table Role */
  lazy val Role = new TableQuery(tag => new Role(tag))

  /** Entity class storing rows of table RolePermission
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param roleId Database column role_id SqlType(TINYINT UNSIGNED)
   *  @param permissionId Database column permission_id SqlType(TINYINT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class RolePermissionRow(id: Int, roleId: Byte, permissionId: Byte, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching RolePermissionRow objects using plain SQL queries */
  implicit def GetResultRolePermissionRow(implicit e0: GR[Int], e1: GR[Byte], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[RolePermissionRow] = GR{
    prs => import prs._
    RolePermissionRow.tupled((<<[Int], <<[Byte], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table role_permission. Objects of this class serve as prototypes for rows in queries. */
  class RolePermission(_tableTag: Tag) extends profile.api.Table[RolePermissionRow](_tableTag, Some("craftal"), "role_permission") {
    def * = (id, roleId, permissionId, createdAt, updatedAt, isDeleted) <> (RolePermissionRow.tupled, RolePermissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(roleId), Rep.Some(permissionId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> RolePermissionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column role_id SqlType(TINYINT UNSIGNED) */
    val roleId: Rep[Byte] = column[Byte]("role_id")
    /** Database column permission_id SqlType(TINYINT UNSIGNED) */
    val permissionId: Rep[Byte] = column[Byte]("permission_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Permission (database name role_permission_ibfk_2) */
    lazy val permissionFk = foreignKey("role_permission_ibfk_2", permissionId, Permission)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Role (database name role_permission_ibfk_1) */
    lazy val roleFk = foreignKey("role_permission_ibfk_1", roleId, Role)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (roleId,permissionId) (database name role_id) */
    val index1 = index("role_id", (roleId, permissionId), unique=true)
  }
  /** Collection-like TableQuery object for table RolePermission */
  lazy val RolePermission = new TableQuery(tag => new RolePermission(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true)
   *  @param email Database column email SqlType(VARCHAR), Length(255,true)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class UserRow(id: Int, name: String, email: String, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("craftal"), "user") {
    def * = (id, name, email, createdAt, updatedAt, isDeleted) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(email), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column email SqlType(VARCHAR), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Uniqueness Index over (email) (database name email) */
    val index1 = index("email", email, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /** Entity class storing rows of table UserIdentity
   *  @param userId Database column user_id SqlType(INT UNSIGNED), PrimaryKey
   *  @param isActivated Database column is_activated SqlType(BIT), Default(false)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class UserIdentityRow(userId: Int, isActivated: Boolean = false, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching UserIdentityRow objects using plain SQL queries */
  implicit def GetResultUserIdentityRow(implicit e0: GR[Int], e1: GR[Boolean], e2: GR[java.sql.Timestamp]): GR[UserIdentityRow] = GR{
    prs => import prs._
    UserIdentityRow.tupled((<<[Int], <<[Boolean], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table user_identity. Objects of this class serve as prototypes for rows in queries. */
  class UserIdentity(_tableTag: Tag) extends profile.api.Table[UserIdentityRow](_tableTag, Some("craftal"), "user_identity") {
    def * = (userId, isActivated, createdAt, updatedAt, isDeleted) <> (UserIdentityRow.tupled, UserIdentityRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(isActivated), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> UserIdentityRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(INT UNSIGNED), PrimaryKey */
    val userId: Rep[Int] = column[Int]("user_id", O.PrimaryKey)
    /** Database column is_activated SqlType(BIT), Default(false) */
    val isActivated: Rep[Boolean] = column[Boolean]("is_activated", O.Default(false))
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing User (database name user_identity_ibfk_1) */
    lazy val userFk = foreignKey("user_identity_ibfk_1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table UserIdentity */
  lazy val UserIdentity = new TableQuery(tag => new UserIdentity(tag))

  /** Entity class storing rows of table UserIdentityPassword
   *  @param userId Database column user_id SqlType(INT UNSIGNED), PrimaryKey
   *  @param hasher Database column hasher SqlType(VARCHAR), Length(255,true)
   *  @param password Database column password SqlType(VARCHAR), Length(255,true)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class UserIdentityPasswordRow(userId: Int, hasher: String, password: String, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching UserIdentityPasswordRow objects using plain SQL queries */
  implicit def GetResultUserIdentityPasswordRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[UserIdentityPasswordRow] = GR{
    prs => import prs._
    UserIdentityPasswordRow.tupled((<<[Int], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table user_identity_password. Objects of this class serve as prototypes for rows in queries. */
  class UserIdentityPassword(_tableTag: Tag) extends profile.api.Table[UserIdentityPasswordRow](_tableTag, Some("craftal"), "user_identity_password") {
    def * = (userId, hasher, password, createdAt, updatedAt, isDeleted) <> (UserIdentityPasswordRow.tupled, UserIdentityPasswordRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(hasher), Rep.Some(password), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> UserIdentityPasswordRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(INT UNSIGNED), PrimaryKey */
    val userId: Rep[Int] = column[Int]("user_id", O.PrimaryKey)
    /** Database column hasher SqlType(VARCHAR), Length(255,true) */
    val hasher: Rep[String] = column[String]("hasher", O.Length(255,varying=true))
    /** Database column password SqlType(VARCHAR), Length(255,true) */
    val password: Rep[String] = column[String]("password", O.Length(255,varying=true))
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing User (database name user_identity_password_ibfk_1) */
    lazy val userFk = foreignKey("user_identity_password_ibfk_1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table UserIdentityPassword */
  lazy val UserIdentityPassword = new TableQuery(tag => new UserIdentityPassword(tag))

  /** Entity class storing rows of table UserIdentityToken
   *  @param token Database column token SqlType(VARCHAR), PrimaryKey, Length(36,true)
   *  @param userId Database column user_id SqlType(INT UNSIGNED)
   *  @param expiredAt Database column expired_at SqlType(DATETIME)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class UserIdentityTokenRow(token: String, userId: Int, expiredAt: java.sql.Timestamp, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching UserIdentityTokenRow objects using plain SQL queries */
  implicit def GetResultUserIdentityTokenRow(implicit e0: GR[String], e1: GR[Int], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[UserIdentityTokenRow] = GR{
    prs => import prs._
    UserIdentityTokenRow.tupled((<<[String], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table user_identity_token. Objects of this class serve as prototypes for rows in queries. */
  class UserIdentityToken(_tableTag: Tag) extends profile.api.Table[UserIdentityTokenRow](_tableTag, Some("craftal"), "user_identity_token") {
    def * = (token, userId, expiredAt, createdAt, updatedAt, isDeleted) <> (UserIdentityTokenRow.tupled, UserIdentityTokenRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(token), Rep.Some(userId), Rep.Some(expiredAt), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> UserIdentityTokenRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column token SqlType(VARCHAR), PrimaryKey, Length(36,true) */
    val token: Rep[String] = column[String]("token", O.PrimaryKey, O.Length(36,varying=true))
    /** Database column user_id SqlType(INT UNSIGNED) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column expired_at SqlType(DATETIME) */
    val expiredAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("expired_at")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing User (database name user_identity_token_ibfk_1) */
    lazy val userFk = foreignKey("user_identity_token_ibfk_1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table UserIdentityToken */
  lazy val UserIdentityToken = new TableQuery(tag => new UserIdentityToken(tag))

  /** Entity class storing rows of table UserRole
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(INT UNSIGNED)
   *  @param roleId Database column role_id SqlType(TINYINT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class UserRoleRow(id: Int, userId: Int, roleId: Byte, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching UserRoleRow objects using plain SQL queries */
  implicit def GetResultUserRoleRow(implicit e0: GR[Int], e1: GR[Byte], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[UserRoleRow] = GR{
    prs => import prs._
    UserRoleRow.tupled((<<[Int], <<[Int], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table user_role. Objects of this class serve as prototypes for rows in queries. */
  class UserRole(_tableTag: Tag) extends profile.api.Table[UserRoleRow](_tableTag, Some("craftal"), "user_role") {
    def * = (id, userId, roleId, createdAt, updatedAt, isDeleted) <> (UserRoleRow.tupled, UserRoleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(roleId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> UserRoleRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(INT UNSIGNED) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column role_id SqlType(TINYINT UNSIGNED) */
    val roleId: Rep[Byte] = column[Byte]("role_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Role (database name user_role_ibfk_2) */
    lazy val roleFk = foreignKey("user_role_ibfk_2", roleId, Role)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name user_role_ibfk_1) */
    lazy val userFk = foreignKey("user_role_ibfk_1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (userId,roleId) (database name user_id) */
    val index1 = index("user_id", (userId, roleId), unique=true)
  }
  /** Collection-like TableQuery object for table UserRole */
  lazy val UserRole = new TableQuery(tag => new UserRole(tag))
}
