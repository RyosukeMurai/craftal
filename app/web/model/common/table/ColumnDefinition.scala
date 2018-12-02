package web.model.common.table

case class ColumnDefinition(key: String, label: String, order: Int, actions: Option[Seq[Any]] = None)
