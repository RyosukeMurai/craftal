package data

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
  lazy val schema: profile.SchemaDescription = Array(ArtistPhoto.schema, Event.schema, EventArtist.schema, EventLocation.schema, EventPhoto.schema, EventSchedule.schema, EventStatus.schema, Genre.schema, Photo.schema, PlayEvolutions.schema, User.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table ArtistPhoto
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
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

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
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

    /** Foreign key referencing Photo (database name artist_photo_ibfk_2) */
    lazy val photoFk = foreignKey("artist_photo_ibfk_2", photoId, Photo)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name artist_photo_ibfk_1) */
    lazy val userFk = foreignKey("artist_photo_ibfk_1", artistId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ArtistPhoto */
  lazy val ArtistPhoto = new TableQuery(tag => new ArtistPhoto(tag))

  /** Entity class storing rows of table Event
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param title Database column title SqlType(VARCHAR), Length(255,true)
   *  @param description Database column description SqlType(TEXT)
   *  @param statusId Database column status_id SqlType(TINYINT UNSIGNED)
   *  @param locationId Database column location_id SqlType(TINYINT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class EventRow(id: Int, title: String, description: String, statusId: Byte, locationId: Byte, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching EventRow objects using plain SQL queries */
  implicit def GetResultEventRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Byte], e3: GR[java.sql.Timestamp], e4: GR[Boolean]): GR[EventRow] = GR{
    prs => import prs._
    EventRow.tupled((<<[Int], <<[String], <<[String], <<[Byte], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table event. Objects of this class serve as prototypes for rows in queries. */
  class Event(_tableTag: Tag) extends profile.api.Table[EventRow](_tableTag, Some("craftal"), "event") {
    def * = (id, title, description, statusId, locationId, createdAt, updatedAt, isDeleted) <> (EventRow.tupled, EventRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(title), Rep.Some(description), Rep.Some(statusId), Rep.Some(locationId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> EventRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
   *  @param userId Database column user_id SqlType(INT UNSIGNED)
   *  @param positionNo Database column position_no SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class EventArtistRow(id: Int, eventId: Int, userId: Int, positionNo: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching EventArtistRow objects using plain SQL queries */
  implicit def GetResultEventArtistRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Boolean]): GR[EventArtistRow] = GR{
    prs => import prs._
    EventArtistRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table event_artist. Objects of this class serve as prototypes for rows in queries. */
  class EventArtist(_tableTag: Tag) extends profile.api.Table[EventArtistRow](_tableTag, Some("craftal"), "event_artist") {
    def * = (id, eventId, userId, positionNo, createdAt, updatedAt, isDeleted) <> (EventArtistRow.tupled, EventArtistRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(eventId), Rep.Some(userId), Rep.Some(positionNo), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> EventArtistRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column event_id SqlType(INT UNSIGNED) */
    val eventId: Rep[Int] = column[Int]("event_id")
    /** Database column user_id SqlType(INT UNSIGNED) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column position_no SqlType(INT UNSIGNED) */
    val positionNo: Rep[Int] = column[Int]("position_no")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Event (database name event_artist_ibfk_1) */
    lazy val eventFk = foreignKey("event_artist_ibfk_1", eventId, Event)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name event_artist_ibfk_2) */
    lazy val userFk = foreignKey("event_artist_ibfk_2", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table EventArtist */
  lazy val EventArtist = new TableQuery(tag => new EventArtist(tag))

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
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
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

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
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
   *  @param id Database column id SqlType(INT UNSIGNED), PrimaryKey
   *  @param eventId Database column event_id SqlType(INT UNSIGNED)
   *  @param venue Database column venue SqlType(VARCHAR), Length(255,true)
   *  @param mapCoordinate Database column map_coordinate SqlType(GEOMETRY)
   *  @param stateTime Database column state_time SqlType(DATETIME)
   *  @param endTime Database column end_time SqlType(DATETIME)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class EventScheduleRow(id: Int, eventId: Int, venue: String, mapCoordinate: java.sql.Blob, stateTime: java.sql.Timestamp, endTime: java.sql.Timestamp, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching EventScheduleRow objects using plain SQL queries */
  implicit def GetResultEventScheduleRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Blob], e3: GR[java.sql.Timestamp], e4: GR[Boolean]): GR[EventScheduleRow] = GR{
    prs => import prs._
    EventScheduleRow.tupled((<<[Int], <<[Int], <<[String], <<[java.sql.Blob], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table event_schedule. Objects of this class serve as prototypes for rows in queries. */
  class EventSchedule(_tableTag: Tag) extends profile.api.Table[EventScheduleRow](_tableTag, Some("craftal"), "event_schedule") {
    def * = (id, eventId, venue, mapCoordinate, stateTime, endTime, createdAt, updatedAt, isDeleted) <> (EventScheduleRow.tupled, EventScheduleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(eventId), Rep.Some(venue), Rep.Some(mapCoordinate), Rep.Some(stateTime), Rep.Some(endTime), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> EventScheduleRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column event_id SqlType(INT UNSIGNED) */
    val eventId: Rep[Int] = column[Int]("event_id")
    /** Database column venue SqlType(VARCHAR), Length(255,true) */
    val venue: Rep[String] = column[String]("venue", O.Length(255,varying=true))
    /** Database column map_coordinate SqlType(GEOMETRY) */
    val mapCoordinate: Rep[java.sql.Blob] = column[java.sql.Blob]("map_coordinate")
    /** Database column state_time SqlType(DATETIME) */
    val stateTime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("state_time")
    /** Database column end_time SqlType(DATETIME) */
    val endTime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("end_time")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(DATETIME) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_deleted SqlType(BIT), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))

    /** Foreign key referencing Event (database name event_schedule_ibfk_1) */
    lazy val eventFk = foreignKey("event_schedule_ibfk_1", eventId, Event)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
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

  /** Entity class storing rows of table Photo
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param fileIdentifier Database column file_identifier SqlType(VARCHAR), Length(255,true)
   *  @param fileName Database column file_name SqlType(VARCHAR), Length(255,true)
   *  @param userId Database column user_id SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param updatedAt Database column updated_at SqlType(DATETIME)
   *  @param isDeleted Database column is_deleted SqlType(BIT), Default(false) */
  case class PhotoRow(id: Int, fileIdentifier: String, fileName: String, userId: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isDeleted: Boolean = false)
  /** GetResult implicit for fetching PhotoRow objects using plain SQL queries */
  implicit def GetResultPhotoRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[PhotoRow] = GR{
    prs => import prs._
    PhotoRow.tupled((<<[Int], <<[String], <<[String], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table photo. Objects of this class serve as prototypes for rows in queries. */
  class Photo(_tableTag: Tag) extends profile.api.Table[PhotoRow](_tableTag, Some("craftal"), "photo") {
    def * = (id, fileIdentifier, fileName, userId, createdAt, updatedAt, isDeleted) <> (PhotoRow.tupled, PhotoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(fileIdentifier), Rep.Some(fileName), Rep.Some(userId), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isDeleted)).shaped.<>({r=>import r._; _1.map(_=> PhotoRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
