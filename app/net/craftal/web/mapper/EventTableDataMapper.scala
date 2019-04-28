package net.craftal.web.mapper

import net.craftal.core.domain.model.event.Event
import net.craftal.web.model.common.table.{ColumnDefinition, Table}
import play.api.i18n.Messages


object EventTableDataMapper extends DataMapper {

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
