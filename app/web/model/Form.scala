package web.model

import play.api.data.Form
import play.api.mvc.Call

object FieldType extends Enumeration {
  type FieldType = Value
  val text, select, checkbox, radio = Value
}


case class FieldDefinition(name: String,
                           label: String,
                           order: Int,
                           filedType: FieldType.FieldType = FieldType.text,
                          )

abstract case class FormDefinition[T](private val fieldDefinitions: Seq[FieldDefinition],
                                      form: Form[T],
                                      submitAction: Call,
                                      cancelAction: Call) {
  lazy val fields: Seq[FieldDefinition] = this.fieldDefinitions.sortWith(_.order < _.order)
}
