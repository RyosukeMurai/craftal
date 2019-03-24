package net.craftal.web.model.common.table

class Table[A](definitions: Seq[ColumnDefinition], items: Seq[Map[String, Any]]) {

  lazy val columns: Seq[Column] =
    this.definitions.sortWith(_.order < _.order).map(d => Column(d.key, d.label))

  lazy val rows: Seq[Row] =
    this.items.map(item => Row(
      this.columns.map(c => Cell(item.get(c.key)))
    ))

}
