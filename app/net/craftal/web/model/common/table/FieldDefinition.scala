package net.craftal.web.model.common.table

case class FieldDefinition(name: String,
                           label: String,
                           order: Int,
                           filedType: FieldType.FieldType = FieldType.text,
                           options: Option[Seq[(String, String)]] = None)
