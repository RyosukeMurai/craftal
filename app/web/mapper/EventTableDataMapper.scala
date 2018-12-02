package web.mapper

import domain.model.event._
import play.api.i18n.Messages
import web.model.common.table.{ColumnDefinition, Table}


object EventTableDataMapper {

  def transform(eventCollection: List[Event])(implicit messages: Messages): Table[Event] =
    new Table(
      Seq(
        ColumnDefinition("id", Messages("craftal.management.event.filed.id"), 1),
        ColumnDefinition("title", Messages("craftal.management.event.filed.title"), 2),
        ColumnDefinition("description", Messages("craftal.management.event.filed.description"), 3)
      ),
      DataMappingUtility.caseToMap(eventCollection)
    )
}
