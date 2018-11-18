package notification

case class NotificationRequest(destinationIdentifier: Seq[String],
                               senderIdentifier: String,
                               title: String,
                               plainFormatContent: Option[String],
                               richFormatContent: Option[String]) {} //TODO(RyosukeMurai): validate format
